(ns cardboard.string-cleaning
  (:require [cardboard.chars :as c]))

(defn unavailable-chars [string]
  (->> (seq string)
       (filter #(not (contains? (c/available-chars) %)))
       distinct))

(defn clean [string]
  (->> (reduce #(remove (partial = %2) %1) string (unavailable-chars string))
       (apply str)))