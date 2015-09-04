(ns cardboard.size
  (:require [cardboard.pattern :as pat]
            [cardboard.constants :refer :all]))

(defn pattern-size [pattern]
  [(count (first pattern))
   (count pattern)])

(defn screen-size [pattern-pixel-size content]
  (let [size-in-pattern-pixels (pattern-size (pat/string->pattern (str content)))
        minimum-width 6]
    [(* (inc (pattern-pixel-size :width)) (max (first size-in-pattern-pixels) minimum-width))
     :by
     (* (inc (pattern-pixel-size :height)) (last size-in-pattern-pixels))]))