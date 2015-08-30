(ns cardboard.pixel-size
  (:require [cardboard.core :as cc]
            [cardboard.constants :refer :all]))

(defn size [string]
  (let [pattern (cc/pattern-in-rows string)]
    [(count (first pattern))
     (count pattern)]))

(defn absolute-size [pixel-size string]
  (let [size-in-pixels (size string)
        minimum-width 6]
    [(* (pixel-size :width) (max (first size-in-pixels) minimum-width))
     :by
     (+ status-bar-height button-bar-height (* (pixel-size :height) (last size-in-pixels)))]))
;; TODO: Better naming. With the addition to the height, it's bound to the character windows