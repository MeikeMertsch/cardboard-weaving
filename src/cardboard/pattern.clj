(ns cardboard.pattern
  (:require [cardboard.default_chars :as dl]
            [cardboard.constants :refer :all]))

(defn unavailable-chars [string]
  (->> string
       (filter #(not (contains? dl/available-chars (str %))))
       distinct
       (map str)))

(defn clean [string]
  (reduce #(clojure.string/replace %1 %2 "") string (unavailable-chars string)))

(defn str->chars [string]
  (->> (seq string)
       (map str)))

(defn letter-patterns->matrix [letter]
  (->> (clojure.string/split-lines letter)
       (map clojure.string/trim)
       (map str->chars)))

(defn create-the-pattern [letters]
  (->> (map dl/char->pattern letters)
       (interpose dl/letter-space)
       (map letter-patterns->matrix)
       (apply map concat)))

(defn string->pattern [string]
  (->> string
       clean
       str->chars
       create-the-pattern))

(defn validate [string]
  (if (empty? (unavailable-chars string))
    {:outcome :ok
     :valid string}
    {:outcome :not-ok
     :error (unavailable-chars string)
     :valid (clean string)}))