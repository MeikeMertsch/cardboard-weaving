(ns cardboard.core
  (:require [cardboard.pattern :as p]
            [cardboard.print_layout :as l]
            [cardboard.saving :as s]
            [cardboard.validation :as v]
            [cardboard.chars :as c]))

(defn save-instructions-for [string file-path]
  (->> (p/string->pattern string)
       (l/string-n-pattern->layout (:valid (v/validate string)))
       (s/save file-path)))

(defn save-character [character pattern]
  (s/save (s/character-location character)
          (p/pattern->string pattern))
  (c/update-mapping))