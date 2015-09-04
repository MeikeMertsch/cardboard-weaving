(ns cardboard.string-cleaning
  (:require [cardboard.font :as f]))

(defn unavailable-chars [string]
  (->> string
       (filter #(not (contains? (f/available-chars) %)))
       distinct))

(defn clean [string]
  (->> (reduce #(remove (partial = %2) %1) string (unavailable-chars string))
       (apply str)))