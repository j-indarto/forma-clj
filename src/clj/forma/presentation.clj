(ns forma.presentation
  (:use [forma.matrix.utils :only (logical-replace)]
        [forma.date-time :only (msecs-from-epoch delta-periods ordinal)]
        [forma.trends.data :only (bimonth-ndvi)]
        [forma.trends.filter :only (hp-filter make-reliable tele-ts deseasonalize)]
        [forma.trends.analysis :only (hansen-stat)]
        [clj-time.core :only (date-time)])
  (:require [incanter.charts :as c]
            [incanter.core :as i]))

(def ndvi-range
  (let [long-range (for [yr (range 2000 2010) mo (range 1 13) day [1 15]]
                     (msecs-from-epoch (date-time yr mo day)))]
    (take (count bimonth-ndvi) long-range)))


(def hp-range
  (concat (map float (range 0 5 0.01)) (range 5 1600)))

(def ndvi-plot
  (c/time-series-plot ndvi-range
                      bimonth-ndvi :x-label "" :y-label ""))

(def hp-plot
  "returns an incanter plot, ready for slider adjustment, that
  displays the NDVI timeseries smoothed by the HP-filter; initial lambda
  value of 20.  Sets parameters for graphing (labels and y-range)."
  (doto (c/time-series-plot ndvi-range
                            (hp-filter 20 bimonth-ndvi)
                            :x-label "" :y-label "")
    (c/set-y-range 0.25 0.92)))

(defn add-ndvi-slider
  [x y plot]
  (c/slider #(i/set-data plot [x (hp-filter % y)]) hp-range "lambda"))

(defn add-ndvi [plot]
  (c/add-lines plot ndvi-range bimonth-ndvi :series-label ""))

(defn add-deseas-ndvi [plot]
  (c/add-lines plot ndvi-range (deseasonalize 23 bimonth-ndvi) :series-label ""))

(defn presentation-view
  [k]
  (cond
   (= k :hp-filter) (do (i/view hp-plot)
                        (add-ndvi hp-plot)
                        (add-ndvi-slider ndvi-range bimonth-ndvi hp-plot))
   (= k :deseas) (do (i/view hp-plot)
                     (add-deseas-ndvi hp-plot)
                     (add-ndvi-slider ndvi-range (deseasonalize 23 bimonth-ndvi) hp-plot))))

;; This is a presentation to show how the filtering techniques work on
;; time-series data.  We can show the Hodrick-Prescott filter with a
;; slider bar to adjust the smoothing parameter.  We can also show how
;; to interpolate across "holes" as determined by the (fake)
;; reliability time-series

;; Create sample NDVI data for a single pixel, so that we can graph
;; the filtering techniques on sample data. Also a rain time-series.

;; Create sample reliability time-series, assuming that any NDVI value
;; below 7000 is "bad". A value of "2" indicates an offending value;
;; all other observations are represented by "1".

;; (def reli
;;   (->> ndvi
;;        (logical-replace < 7000 2)
;;        (logical-replace > 2 1)))

;; Mark all offending values in order to graph the "kill points" 

;; (def kill-vals
;;   (logical-replace > 7000 nil ndvi))

;; Create a time range for the x-axis on the time-series graph; NOTE
;; that this will have to change, once the time functions are moved to
;; conversion.

;; (def forma-range
;;   (monthly-msec-range (date-time 2000 2)
;;                       (date-time 2010 12)))

;; Initialize the first plot with the raw NDVI time-series; the
;; default color for this line is blue.  In order to view this plot,
;; you only need to type (i/view plot1) in the REPL.

;; (def plot1
;;   (doto (c/time-series-plot forma-range
;;                             ndvi
;;                             :title "NDVI with Unreliable Measurements"
;;                             :x-label ""
;;                             :y-label ""
;;                             :legend true
;;                             :series-label "NDVI")))

;; Add the points indicating bad (read: unreliable) values on the
;; original NDVI time-series.

;; (defn add-unreliable []
;;   (c/add-points plot1 forma-range
;;                 kill-vals
;;                 :series-label "Unreliable Values"))

;; Create a new plot with the HP-filter to be updated by the slider,
;; based on the interpolated NDVI values.

;; (def plot2 (c/time-series-plot forma-range
;;                                (hp-filter (make-reliable #{2} #{1} reli ndvi) 2)
;;                                :title ""
;;                                :x-label ""
;;                                :y-label ""
;;                                :series-label ""))

;; Add a plot of the interpolated NDVI values to serve as the
;; background to the updating slider.

;; (defn add-conditioned-ndvi []
;;   (c/add-lines plot2
;;                forma-range
;;                (make-reliable #{2} #{1} reli ndvi)
;;                :series-label ""))

;; Add the slider to the original time-series plot, defined in the
;; first definition of plot2

;; (defn add-ndvi-slider []
;;   (let [x forma-range]
;;     (c/slider #(i/set-data plot2
;;                            [x (hp-filter (make-reliable #{2} #{1} reli ndvi) %)])
;;               (map (partial Math/round)
;;                    (range 0 1000 1))
;;               "lambda")))

;; Add the original kill plots, just in case we want to show the
;; original scale. Note that we can just turn this feature on and off.

;; (defn add-kill-points []
;;   (c/add-points plot2
;;                 forma-range
;;                 kill-vals
;;                 :shape 1
;;                 :series-label "UNRELIABLE"))

;; Initialize the first plot with the raw NDVI time-series; the
;; default color for this line is blue.  In order to view this plot,
;; you only need to type (i/view plot1) in the REPL.

;; (def plot3
;;   (doto (c/time-series-plot forma-range
;;                             ndvi
;;                             :title ""
;;                             :x-label ""
;;                             :y-label ""
;;                             :legend true
;;                             :series-label "NDVI")
;;     (c/set-stroke-color java.awt.Color/green)))


;; Add the points indicating bad (read: unreliable) values on the
;; original NDVI time-series.

;; (defn add-unreliable-views []
;;   (c/add-points plot3
;;                 forma-range
;;                 kill-vals
;;                 :series-label
;;                 "UNRELIABLE VALUES"))

;; (defn add-filter []
;;   (doto (c/add-lines plot3
;;                      forma-range
;;                      (hp-filter (make-reliable #{2} #{1} reli ndvi) 2)
;;                      :series-label "FILTER")))


;; Data from BFAST-Lite

;; (def Yt [0.9 0.89 0.88 0.88 0.89 0.9 0.89 0.89 0.87 0.88 0.86 0.83 0.8 0.77 0.76 0.82 0.79 0.82 0.83 0.82 0.84 0.82 0.86 0.89 0.87 0.9 0.88 0.87 0.89 0.9 0.84 0.87 0.85 0.81 0.76 0.74 0.77 0.79 0.75 0.69 0.69 0.69 0.72 0.72 0.76 0.78 0.78 0.82 0.85 0.85 0.83 0.86 0.85 0.86 0.84 0.82 0.82 0.81 0.78 0.75 0.77 0.73 0.75 0.73 0.75 0.77 0.77 0.76 0.77 0.79 0.79 0.79 0.81 0.85 0.84 0.87 0.86 0.82 0.82 0.83 0.81 0.79 0.79 0.76 0.79 0.75 0.77 0.8 0.8 0.83 0.84 0.85 0.85 0.84 0.84 0.84 0.88 0.86 0.85 0.86 0.86 0.86 0.85 0.84 0.73 0.62 0.66 0.58 0.52 0.45 0.42 0.39 0.4 0.42 0.48 0.52 0.56 0.52 0.49 0.49 0.45 0.47 0.53 0.48 0.44 0.42 0.42 0.42 0.38 0.32 0.34 0.31 0.3 0.3 0.3 0.29 0.34 0.32 0.33 0.35 0.36 0.37 0.38 0.38 0.38 0.45 0.4 0.37 0.39 0.38 0.33 0.31 0.33 0.39 0.47 0.43 0.41 0.43 0.43 0.43 0.45 0.46 0.49 0.52 0.55 0.6 0.59 0.65 0.64 0.64 0.61 0.62 0.55 0.54 0.54 0.56 0.53 0.59 0.63 0.68 0.69 0.68 0.74 0.72 0.71 0.68 0.7 0.69 0.73 0.72 0.72 0.73 0.76 0.71 0.76 0.69 0.65 0.64 0.68])

;; (def Vt [0.885253108230368 0.867280574515708 0.848619763813249 0.848270117939465 0.840321171004965 0.852608740730509 0.83182804577926 0.822557077211613 0.815893287781789 0.845381087138439 0.824984229141628 0.824613794222812 0.828493151321644 0.821154795590607 0.804098703250892 0.868972328861596 0.845399730666543 0.880472193038357 0.882164146510947 0.866473225193443 0.880242490406173 0.840351425904802 0.86456680969001 0.875253108230368 0.847280574515708 0.86861976381325 0.848270117939465 0.820321171004965 0.842608740730509 0.84182804577926 0.772557077211613 0.815893287781789 0.815381087138439 0.774984229141628 0.754613794222812 0.768493151321644 0.821154795590607 0.834098703250892 0.798972328861596 0.745399730666543 0.750472193038357 0.742164146510947 0.766473225193443 0.760242490406173 0.780351425904802 0.78456680969001 0.765253108230368 0.797280574515708 0.818619763813249 0.818270117939465 0.780321171004965 0.812608740730509 0.79182804577926 0.792557077211613 0.785893287781789 0.785381087138439 0.784984229141628 0.804613794222812 0.808493151321644 0.801154795590607 0.814098703250892 0.778972328861596 0.805399730666543 0.790472193038357 0.802164146510947 0.816473225193443 0.810242490406173 0.780351425904802 0.77456680969001 0.775253108230368 0.767280574515708 0.758619763813249 0.778270117939465 0.800321171004965 0.792608740730509 0.81182804577926 0.792557077211613 0.765893287781789 0.785381087138439 0.794984229141628 0.804613794222812 0.818493151321644 0.841154795590607 0.804098703250892 0.838972328861596 0.805399730666543 0.830472193038357 0.852164146510947 0.846473225193443 0.870242490406173 0.860351425904802 0.85456680969001 0.835253108230368 0.817280574515708 0.808619763813249 0.808270117939465 0.830321171004965 0.812608740730509 0.79182804577926 0.792557077211613 0.805893287781789 0.82538108713844 0.814984229141628 0.834613794222812 0.758493151321644 0.671154795590607 0.704098703250892 0.628972328861596 0.575399730666543 0.510472193038357 0.472164146510947 0.436473225193443 0.440242490406173 0.440351425904802 0.48456680969001 0.505253108230368 0.537280574515708 0.488619763813249 0.458270117939465 0.440321171004965 0.402608740730509 0.41182804577926 0.462557077211613 0.425893287781789 0.405381087138439 0.384984229141628 0.414613794222812 0.448493151321644 0.431154795590607 0.364098703250892 0.388972328861596 0.365399730666543 0.360472193038357 0.352164146510947 0.346473225193443 0.330242490406173 0.360351425904803 0.32456680969001 0.315253108230368 0.327280574515708 0.328619763813249 0.338270117939465 0.330321171004965 0.332608740730509 0.32182804577926 0.382557077211613 0.345893287781789 0.335381087138439 0.354984229141628 0.374613794222812 0.358493151321644 0.361154795590607 0.374098703250892 0.438972328861596 0.525399730666543 0.490472193038357 0.462164146510947 0.476473225193443 0.470242490406173 0.450351425904802 0.45456680969001 0.445253108230368 0.467280574515708 0.488619763813249 0.518270117939465 0.550321171004965 0.542608740730509 0.59182804577926 0.572557077211613 0.585893287781789 0.575381087138439 0.584984229141628 0.544613794222812 0.568493151321644 0.591154795590607 0.604098703250892 0.578972328861596 0.645399730666543 0.690472193038357 0.732164146510947 0.736473225193443 0.720242490406173 0.760351425904802 0.72456680969001 0.695253108230368 0.657280574515708 0.668619763813249 0.658270117939465 0.680321171004965 0.672608740730509 0.66182804577926 0.662557077211613 0.705893287781789 0.675381087138439 0.724984229141628 0.684613794222812 0.678493151321644 0.691154795590607 0.724098703250892])

;; (def ti [2000.13043478261 2000.17391304348 2000.21739130435 2000.26086956522 2000.30434782609 2000.34782608696 2000.39130434783 2000.4347826087 2000.47826086957 2000.52173913043 2000.5652173913 2000.60869565217 2000.65217391304 2000.69565217391 2000.73913043478 2000.78260869565 2000.82608695652 2000.86956521739 2000.91304347826 2000.95652173913 2001 2001.04347826087 2001.08695652174 2001.13043478261 2001.17391304348 2001.21739130435 2001.26086956522 2001.30434782609 2001.34782608696 2001.39130434783 2001.4347826087 2001.47826086957 2001.52173913043 2001.5652173913 2001.60869565217 2001.65217391304 2001.69565217391 2001.73913043478 2001.78260869565 2001.82608695652 2001.86956521739 2001.91304347826 2001.95652173913 2002 2002.04347826087 2002.08695652174 2002.13043478261 2002.17391304348 2002.21739130435 2002.26086956522 2002.30434782609 2002.34782608696 2002.39130434783 2002.4347826087 2002.47826086957 2002.52173913043 2002.5652173913 2002.60869565217 2002.65217391304 2002.69565217391 2002.73913043478 2002.78260869565 2002.82608695652 2002.86956521739 2002.91304347826 2002.95652173913 2003 2003.04347826087 2003.08695652174 2003.13043478261 2003.17391304348 2003.21739130435 2003.26086956522 2003.30434782609 2003.34782608696 2003.39130434783 2003.4347826087 2003.47826086957 2003.52173913043 2003.5652173913 2003.60869565217 2003.65217391304 2003.69565217391 2003.73913043478 2003.78260869565 2003.82608695652 2003.86956521739 2003.91304347826 2003.95652173913 2004 2004.04347826087 2004.08695652174 2004.13043478261 2004.17391304348 2004.21739130435 2004.26086956522 2004.30434782609 2004.34782608696 2004.39130434783 2004.4347826087 2004.47826086957 2004.52173913043 2004.5652173913 2004.60869565217 2004.65217391304 2004.69565217391 2004.73913043478 2004.78260869565 2004.82608695652 2004.86956521739 2004.91304347826 2004.95652173913 2005 2005.04347826087 2005.08695652174 2005.13043478261 2005.17391304348 2005.21739130435 2005.26086956522 2005.30434782609 2005.34782608696 2005.39130434783 2005.4347826087 2005.47826086957 2005.52173913043 2005.5652173913 2005.60869565217 2005.65217391304 2005.69565217391 2005.73913043478 2005.78260869565 2005.82608695652 2005.86956521739 2005.91304347826 2005.95652173913 2006 2006.04347826087 2006.08695652174 2006.13043478261 2006.17391304348 2006.21739130435 2006.26086956522 2006.30434782609 2006.34782608696 2006.39130434783 2006.4347826087 2006.47826086957 2006.52173913043 2006.5652173913 2006.60869565217 2006.65217391304 2006.69565217391 2006.73913043478 2006.78260869565 2006.82608695652 2006.86956521739 2006.91304347826 2006.95652173913 2007 2007.04347826087 2007.08695652174 2007.13043478261 2007.17391304348 2007.21739130435 2007.26086956522 2007.30434782609 2007.34782608696 2007.39130434783 2007.4347826087 2007.47826086957 2007.52173913043 2007.5652173913 2007.60869565217 2007.65217391304 2007.69565217391 2007.73913043478 2007.78260869565 2007.82608695652 2007.86956521739 2007.91304347826 2007.95652173913 2008 2008.04347826087 2008.08695652174 2008.13043478261 2008.17391304348 2008.21739130435 2008.26086956522 2008.30434782609 2008.34782608696 2008.39130434783 2008.4347826087 2008.47826086957 2008.52173913043 2008.5652173913 2008.60869565217 2008.65217391304 2008.69565217391 2008.73913043478])

;; Plot the structural break lines, with a slider that can adjust the
;; MOSUM window and the HP filter parameter

;; (def plot-efp (c/xy-plot (range (count Yt))
;;                          (:series (mosum-prediction (i/matrix (hp-filter Yt 10))
;;                                                     (i/bind-columns (repeat (count Yt) 1)
;;                                                                     ti)
;;                                                     0.05
;;                                                     0.05))
;;                          :title ""
;;                          :x-label ""
;;                          :y-label ""
;;                          :series-label ""))

;; (defn add-bfast-ndvi []
;;   (c/add-lines plot-efp (range (count Yt)) Yt))

;; (defn add-bfast-slider []
;;   (let [x (range (count Yt))]
;;     (c/sliders*
;;      #(i/set-data plot-efp
;;                   [x (:series
;;                       (mosum-prediction (i/matrix (hp-filter Yt %1))
;;                                         (i/bind-columns (repeat (count Yt) 1)
;;                                                         ti)
;;                                         %2
;;                                         %3))])
;;      [(map (partial round-places 2)
;;            (range 0 100 1))
;;       [0.05 0.1 0.15 0.2 0.25 0.3 0.35 0.4 0.45 0.5]
;;       [0.01 0.025 0.05 0.1 0.15 0.2]]
;;      ["h-p filter parameter" "h" "p-value"])))


;; Draw graphs for slide presentation

;; (def forma-range
;;   (monthly-msec-range (date-time 2000 2)
;;                       (date-time 2010 12)))

;; (defn bi-interpolate
;;   [coll]
;;   (let [intervening-val (map #(/ (+ %1 %2) 2)
;;                              coll
;;                              (drop 1 coll))]
;;     (concat (interleave coll intervening-val) [(last coll)])))

;; (def bimonth-intervals
;;   (monthly-msec-range (date-time 2003 5)
;;                       (date-time 2011 8)))


;; (def bfast-range (bi-interpolate bimonth-intervals))

;; (def plot-breaks
;;   (doto (c/time-series-plot bfast-range
;;                             Yt
;;                             :title ""
;;                             :x-label ""
;;                             :y-label "NDVI Value"
;;                             :legend false
;;                             :series-label "NDVI")
;;     (c/set-stroke-color java.awt.Color/BLUE)))

;; (defn add-deseasonal []
;;   (c/add-lines plot-breaks bfast-range
;;                Vt))

;; (defn add-break []
;;   (c/add-lines plot-breaks bfast-range
;;                (:series (mosum-prediction (i/matrix (hp-filter Yt 10))
;;                                           (i/bind-columns (repeat (count Yt) 1)
;;                                                           ti)
;;                                           0.05
;;                                           0.05))))
