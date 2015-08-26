(ns cardboard.string-cleaner
  (:require [cardboard.default_chars :as dl]))

(defn unavailable-chars [string]
  (->> string
       (filter #(not (contains? dl/available-chars (str %))))
       distinct
       (map str)))

(defn clean [string]
  (reduce #(clojure.string/replace %1 %2 "") string (unavailable-chars string)))

