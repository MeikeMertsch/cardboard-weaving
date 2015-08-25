(ns cardboard.pattern
  (:require [cardboard.default_letters :as dl]
            [cardboard.constants :refer :all]))

(defn str->chars [string]
  (->> (seq string)
       (map str)))

(defn letter-patterns->matrix [letter]
  (->> (clojure.string/split-lines letter)
       (map clojure.string/trim)
       (map str->chars)))

(defn create-the-pattern [letters]
  (->> (map #(get dl/char->pattern % empty-string) letters)
       (remove empty?)
       (interpose dl/letter-space)
       (map letter-patterns->matrix)
       (apply map concat)))

(defn string->pattern [string]
  (->> (str->chars string)
       (create-the-pattern)))

(defn unavailable-chars [string]
  (->> string
       (filter #(not (contains? dl/available-chars (str %))))
       distinct
       (map str)))

(defn clean-string [string]
  (reduce #(clojure.string/replace %1 %2 "") string (unavailable-chars string)))

(defn validate [string]
  (if (empty? (unavailable-chars string))
    {:outcome :ok
     :valid string}
    {:outcome :not-ok
     :error (unavailable-chars string)
     :valid (clean-string string)}))
