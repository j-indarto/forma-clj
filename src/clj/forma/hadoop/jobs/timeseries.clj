(ns forma.hadoop.jobs.timeseries
  (:use cascalog.api
        [forma.matrix.utils :only (sparse-expander)]
        [forma.date-time :only (datetime->period)]
        [forma.matrix.walk :only (walk-matrix)])
  (:require [forma.utils :as utils]
            [forma.source.modis :as m]
            [forma.hadoop.io :as io]
            [forma.hadoop.predicate :as p]))

(defbufferop [timeseries [missing-val]]
  "Takes in a number of `<t-period, modis-chunk>` tuples,
  sorted by time period, and transposes these into (n = chunk-size)
  4-tuples, formatted as <pixel-idx, t-start, t-end, t-series>, where
  the `t-series` field is represented by an instance of
  `forma.schema.DoubleArray`.

  Entering chunks should be sorted by `t-period` in ascending
  order. `modis-chunk` tuple fields must be vectors or instances of
  `forma.schema.DoubleArray` or `forma.schema.IntArray`, as dictated
  by the Thriftable interface in `forma.hadoop.io`."
  [tuples]
  (let [[periods [val]] (apply map vector tuples)
        [fp lp] ((juxt first peek) periods)
        missing-struct (io/to-struct (repeat (io/count-vals val) missing-val))
        chunks (sparse-expander missing-struct tuples :start fp)
        tupleize (comp (partial vector fp lp)
                       io/to-struct
                       vector)]
    (->> chunks
         (map io/get-vals)
         (apply map tupleize)
         (map-indexed cons))))

(defn form-tseries
  "Returns a predicate macro aggregator that generates a timeseries,
  given `?chunk`, `?temporal-resolution` and `?date`. Currently only
  functions when `?chunk` is an instance of
  `forma.schema.DoubleArray`."
  [missing-val]
  (<- [?temporal-res ?date ?chunk :> ?pix-idx ?t-start ?t-end ?tseries]
      (datetime->period ?temporal-res ?date :> ?tperiod)
      (:sort ?tperiod)
      (timeseries [missing-val] ?tperiod ?chunk :> ?pix-idx ?t-start ?t-end ?tseries)))

(defn extract-tseries
  [chunk-source missing-val]
  (let [mk-tseries (form-tseries missing-val)]
    (<- [?dataset ?s-res ?t-res ?tilestring ?chunk-size
         ?chunkid ?pix-idx ?t-start ?t-end ?tseries]
        (chunk-source ?dataset ?s-res ?t-res ?tilestring ?date ?chunkid ?chunk)
        (io/count-vals ?chunk :> ?chunk-size)
        (mk-tseries ?t-res ?date ?chunk :> ?pix-idx ?t-start ?t-end ?tseries))))

(defn process-tseries
  "Given a source of timeseries, this subquery extracts the proper
  position information and outputs the timeseries tagged well."
  [tseries-source]
  (<- [?dataset ?s-res ?t-res ?tile-h ?tile-v ?sample ?line ?t-start ?t-end ?tseries]
      (tseries-source ?dataset ?s-res ?t-res ?tilestring
                      ?chunk-size ?chunkid ?pix-idx ?t-start ?t-end ?tseries)
      (m/tilestring->hv ?tilestring :> ?tile-h ?tile-v)
      (m/tile-position ?s-res ?chunk-size ?chunkid ?pix-idx :> ?sample ?line)))

(gen-class :name forma.hadoop.jobs.DynamicTimeseries
           :prefix "dynamic-timeseries-")

(def *missing-val* -9999)

(defn tseries-query [in-path]
  (-> (hfs-seqfile in-path)
      (extract-tseries *missing-val*)
      process-tseries))

;; TODO: Process a pattern, here, as below. Can we grab rain and ndvi
;; timeseries at the same time?

(defn dynamic-timeseries-main
  [in-path output-path]
  (?- (hfs-seqfile output-path)
      (tseries-query in-path)))

;; This is the old one, that allows for the pieces. We'll reinstitute
;; it when I'm sure that the pieces get processed properly... not sure
;; how `map read-string` does.
;;
;; (defn -main
;;   "TODO: Docs.

;;   Sample usage:

;;       (-main s3n://redddata/ /timeseries/ \"ndvi\"
;;              \"1000-32\" [\"008006\" \"008009\"])"
;;   [base-input-path output-path & pieces]
;;   (let [pieces (map read-string pieces)
;;         chunk-source (apply io/chunk-tap base-input-path pieces)]
;;     (?- (hfs-seqfile output-path)
;;         (-> chunk-source
;;             extract-tseries
;;             process-tseries))))

;; #### Fire Time Series Processing

(defaggregateop merge-firetuples
  " Aggregates a number of firetuples by adding up the values
  of each `FireTuple` property."
  ([] [0 0 0 0])
  ([state tuple] (map + state (io/extract-fields tuple)))
  ([state] [(apply io/fire-tuple state)]))

;; Special case of `running-sum` for `FireTuple` thrift objects.
(defmapop running-fire-sum
  [tseries]
  (let [empty (io/fire-tuple 0 0 0 0)]
    (->> tseries
         (utils/running-sum [] empty io/add-fires)
         io/fire-series)))

(defn aggregate-fires
  "Converts the datestring into a time period based on the supplied
  temporal resolution."
  [t-res src]
  (<- [?dataset ?m-res ?new-t-res ?tilestring ?tperiod ?sample ?line ?newtuple]
      (p/add-fields t-res :> ?new-t-res)
      (src ?dataset ?m-res ?t-res ?tilestring ?datestring ?sample ?line ?tuple)
      (datetime->period ?new-t-res ?datestring :> ?tperiod)
      (merge-firetuples ?tuple :> ?newtuple)))

(defn fire-series
  "Aggregates fires into timeseries."
  [t-res start end src]
  (let [[start end] (map (partial datetime->period t-res) [start end])
        length (-> end (- start) inc)
        mk-fire-tseries (p/vals->sparsevec start length (io/fire-tuple 0 0 0 0))]
    (<- [?dataset ?m-res ?t-res ?mod-h ?mod-v ?sample ?line ?t-start ?t-end ?ct-series]
        (src ?dataset ?m-res ?t-res ?tilestring ?tperiod ?sample ?line ?tuple)
        (m/tilestring->hv ?tilestring :> ?mod-h ?mod-v)
        (mk-fire-tseries ?tperiod ?tuple :> _ ?tseries)
        (p/add-fields start end :> ?t-start ?t-end)
        (running-fire-sum ?tseries :> ?ct-series))))

(gen-class :name forma.hadoop.jobs.FireTimeseries
           :prefix "fire-timeseries-")

(defn fire-query
  [t-res start end chunk-path]
  (->> (hfs-seqfile chunk-path)
       (aggregate-fires t-res)
       (fire-series t-res start end)))

(defn fire-timeseries-main
  [t-res start end chunk-path tseries-path]
  (?- (hfs-seqfile tseries-path)
      (fire-query t-res start end chunk-path)))