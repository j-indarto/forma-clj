(ns forma.source.logistic
  (:use [forma.utils :only (dot-product transpose multiply-rows)])
  (:require [incanter.core :as i])
  (:import [org.jblas FloatMatrix MatrixFunctions Solve DoubleMatrix]))

;; TODO: function to correct for error induced by ridge

(defn logistic-fn
  "returns the value of the logistic function, given input `x`"
  [x]
  (let [exp-x (Math/exp x)]
    (/ exp-x (inc exp-x))))

(defn to-double-matrix
  [mat]
  (DoubleMatrix.
   (into-array (map double-array mat))))

(defn logistic-prob
  "returns the probability of a binary outcome given a parameter
  vector `beta-seq` and a feature vector for a given observation"
  [beta-seq feature-seq]
  (logistic-fn (dot-product beta-seq feature-seq)))

(defn log-likelihood
  "returns the log likelihood of a given pixel, conditional on its
  label (0-1) and the probability of label equal to 1."
  [beta-seq label feature-seq]
  (let [prob (logistic-prob beta-seq feature-seq)]
    (+ (* label (Math/log prob))
       (* (- 1 label) (Math/log (- 1 prob))))))

(defn total-log-likelihood
  "returns the total log likelihood for a group of pixels; input
  labels and features for the group of pixels, aligned correctly so
  that the first label and feature correspond to the first pixel."
  [beta-seq label-seq feature-mat]
  (reduce + (map (partial log-likelihood beta-seq) label-seq feature-mat)))

(defn probability-calc
  "returns a vector of probabilities for each observation"
  [beta-seq feature-mat]
  (map (partial logistic-prob beta-seq)
       feature-mat))

(defn score-seq
  "returns the scores for each parameter"
  [beta-seq label-seq feature-mat]
  (let [prob-seq (probability-calc beta-seq feature-mat)
        features (to-double-matrix feature-mat)]
    (.mmul (.transpose features)
           (DoubleMatrix. (double-array (map - label-seq prob-seq))))))

(defn info-matrix
  "returns the square information matrix for the logistic probability
  function; the dimension is given by the number of features"
  [beta-seq labels feature-mat]
  (let [mult-func (fn [x] (* x (- 1 x)))
        prob-seq  (->> (probability-calc beta-seq feature-mat)
                       (map mult-func))
        scale-feat (multiply-rows prob-seq (transpose feature-mat))]
    (.mmul (to-double-matrix scale-feat) (to-double-matrix feature-mat))))

(defn beta-update
  "returns a vector of updates for the parameter vector; the
  ridge-constant is a very small scalar, used to ensure that the
  inverted information matrix is non-singular."
  [beta-seq labels features rdg-cons]
  (let [num-features (count beta-seq)
        info-adj (.addi
                  (info-matrix beta-seq labels features)
                  (.muli (DoubleMatrix/eye (int num-features)) (float rdg-cons)))]
    (.mmul (Solve/solve info-adj (DoubleMatrix/eye (int 20)))
           (score-seq beta-seq labels features))))

(defn logistic-beta-vector
  "return the parameter vector used to "
  [labels features rdg-cons]
  (let [b (repeat 20 0)]
    (loop [beta b iter 10]
      (if (zero? iter)
        beta
        (recur (let [update (beta-update beta labels features rdg-cons)]
                 (map + beta (vec (.toArray update))))
               (dec iter))))))



