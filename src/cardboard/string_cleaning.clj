(ns cardboard.string-cleaning
  (:require [cardboard.chars :as c]))

(defn unavailable-chars [string]
  (->> string
       (filter #(not (contains? (c/available-chars) (str %))))
       distinct
       (map str)))

(defn clean [string]
  (reduce #(clojure.string/replace %1 %2 "") string (unavailable-chars string)))