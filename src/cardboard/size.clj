(ns cardboard.size
  (:require [cardboard.pattern :as pat]
            [cardboard.constants :refer :all]))

(defn pattern-size [pattern]
  [(count (first pattern))
   (count pattern)])

(defn screen-size [pattern-pixel-size string]
  (let [size-in-pattern-pixels (pattern-size (pat/string->pattern string))
        minimum-width 6]
    [(* (pattern-pixel-size :width) (max (first size-in-pattern-pixels) minimum-width))
     :by
     (* (pattern-pixel-size :height) (last size-in-pattern-pixels))]))