(ns cardboard.pattern
  (:require [cardboard.default_chars :as dl]
            [cardboard.string-cleaner :as sc]
            [cardboard.constants :refer :all]))

(defn str->chars [string]
  (->> (seq string)
       (map str)))

(defn char-patterns->matrix [char-patterns]
  (->> (clojure.string/split-lines char-patterns)
       (map clojure.string/trim)
       (map str->chars)))

(defn create-the-pattern [chars]
  (->> (map dl/char->pattern chars)
       (interpose dl/letter-space)
       (map char-patterns->matrix)
       (apply map concat)))

(defn string->pattern [string]
  (->> string
       sc/clean
       str->chars
       create-the-pattern))

(defn validate [string]
  (if (empty? (sc/unavailable-chars string))
    {:outcome :ok
     :valid string}
    {:outcome :not-ok
     :error (sc/unavailable-chars string)
     :valid (sc/clean string)}))