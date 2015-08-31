(ns cardboard.core
  (:require [cardboard.pattern :as p]
            [cardboard.print_layout :as l]
            [cardboard.default_chars :as dc]
            [cardboard.saving :as s]
            [cardboard.validation :as v]
            [cardboard.constants :refer :all]
            [cardboard.chars :as c]))

(defn save-instructions-for [string file-path]
  (->> (p/string->pattern string)
       (l/string-n-pattern->layout (:valid (v/validate string)))
       (s/save file-path)))

(defn save-character [character pattern]
  (s/save (s/character-location character)
          (p/pattern->string pattern))
  (c/update-mapping))

(defn pattern-in-rows [string]
  (if (= empty-string string)
    empty-pattern
    (p/string->pattern string)))

(defn prefill-default-characters []
  (doseq [character dc/available-chars]
    (s/save (s/character-location character) (dc/char->pattern character))))