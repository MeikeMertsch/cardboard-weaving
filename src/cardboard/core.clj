(ns cardboard.core
  (:require [cardboard.saver :refer :all])
  (:require [cardboard.font_interpreter :refer :all])
  (:require [cardboard.font :refer :all]))

(defn instructions-for [string]
  (->> (pattern-of string)
       clojure.string/split-lines
       (map clojure.string/trim)
       (map row-of-pattern)
       (map #(map pack-description %))))

(defn convert-instructions [instructions]
  (->> (map #(clojure.string/join " " %) instructions)
       (interpose "\n")
       clojure.string/join))

(defn save-instructions-for [string]
  (save (convert-instructions (instructions-for string))))
