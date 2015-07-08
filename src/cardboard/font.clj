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
  (->> (apply map list letter)
       (map reverse)))

(defn pattern-of [string]
  (->> (str->chars string)
       (map char->pattern)
       (interpose letter-space)
       (map letter-patterns->matrix)
       (map turn-pattern-90-deg)
       (apply concat)))
