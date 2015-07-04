(ns cardboard.font
  (:require [cardboard.default_letters :refer :all]))

(defn pattern-of [string]
  (->> (seq string)
       (map str)
       (map mapping)
       (interpose offset)
       concat
       (apply str)))
