(ns cardboard.saving
  (:require [cardboard.constants :refer :all]
            [cardboard.print_layout :as l]
            [cardboard.validation :as v]
            [cardboard.pattern :as p]
            [cardboard.font :as f]))

(defn save [file instructions]
  (spit file instructions))

(defn character->file-name [character]
  (-> (int character)
      (str character-extension)))

(defn character->location [character]
  (str default-character-location
       (character->file-name character)))

(defn save-character [character pattern]
  (save (character->location character)
          (p/pattern->string pattern))
  (f/update-mapping!))

(defn save-instructions-for [string file-path]
  (->> (p/string->pattern string)
       (l/string-n-pattern->layout (:valid (v/validate string)))
       (save file-path)))