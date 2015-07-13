(ns cardboard.pattern
  (:require [cardboard.default_letters :refer :all]))

(defn str->chars [string]
  (->> (seq string)
       (map str)))

(defn letter-patterns->matrix [letter]
  (->> (clojure.string/split-lines letter)
       (map clojure.string/trim)
       (map str->chars)))

(defn create-the-pattern [letters]
  (->> (map char->pattern letters)
       (interpose letter-space)
       (map letter-patterns->matrix)))

(defn string->pattern [string]
  (->> (str->chars string)
       (create-the-pattern)))
