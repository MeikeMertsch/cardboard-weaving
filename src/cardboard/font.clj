(ns cardboard.font
  (:require [cardboard.default_letters :refer :all]))

(defn str->chars [string]
  (->> (seq string)
       (map str)))

(defn letter-patterns->matrix [letter]
  (->> (clojure.string/split-lines letter)
       (map clojure.string/trim)
       (map str->chars)))

(defn turn-pattern-90-deg [letter]
  (->> (letter-patterns->matrix letter)
       (apply map list)
       (map reverse)
       (map #(apply str %))
       (interpose "\n")
       (apply str)))

(defn pattern-of [string]
  (->> (str->chars string)
       (map char->pattern)
       (interpose letter-space)
       (map turn-pattern-90-deg)
       (interpose "\n")
       (apply str)))
