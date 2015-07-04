(ns cardboard.font
  (:require [cardboard.default_letters :refer :all]))

(defn str-to-chars [string]
  (->> (seq string)
       (map str)))

(defn turn-pattern-90-deg [letters]
  (->> (clojure.string/split-lines letters)
       (map clojure.string/trim)
       (map str-to-chars)
       (apply map list)
       (map reverse)
       (map #(apply str %))
       (interpose "\n")
       (apply str)))

(defn pattern-of [string]
  (->> (str-to-chars string)
       (map mapping)
       (interpose offset)
       (map turn-pattern-90-deg)
       (interpose "\n")
       (apply str)))
