(ns cardboard.pattern
  (:require [cardboard.font :as f]
            [cardboard.string-cleaning :as sc]
            [cardboard.constants :refer :all]))

(defn char-patterns->matrix [char-patterns]
  (->> (clojure.string/split-lines char-patterns)
       (map clojure.string/trim)
       (map seq)
       (map #(map str %))))

(defn create-the-pattern [chars]
  (->> (map #((f/char->pattern) %) chars)
       (interpose letter-space)
       (map char-patterns->matrix)
       (apply map concat)))

(defn string->pattern [string]
  (if (= empty-string string)
    empty-pattern
    (->> string
         sc/clean
         seq
         create-the-pattern)))

(defn pattern->string [pattern]
  (->> pattern
       (interpose new-line)
       (apply concat)
       (apply str)))