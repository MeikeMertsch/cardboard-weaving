(ns cardboard.pattern
  (:require [cardboard.chars :as c]
            [cardboard.string-cleaning :as sc]
            [cardboard.constants :refer :all]))

(defn str->chars [string]
  (->> (seq string)
       (map str)))

(defn char-patterns->matrix [char-patterns]
  (->> (clojure.string/split-lines char-patterns)
       (map clojure.string/trim)
       (map str->chars)))

(defn create-the-pattern [chars]
  (->> (map #((c/char->pattern) %) chars)
       (interpose (c/letter-space))
       (map char-patterns->matrix)
       (apply map concat)))

(defn string->pattern [string]
  (if (= empty-string string)
    empty-pattern
    (->> string
         sc/clean
         str->chars
         create-the-pattern)))

(defn pattern->string [pattern]
  (->> pattern
       (interpose new-line)
       (apply concat)
       (apply str)))