(ns cardboard.saving
  (:require [cardboard.constants :refer :all]
            [cardboard.print_layout :as l]
            [cardboard.validation :as v]
            [cardboard.pattern :as p]
            [cardboard.font :as f]))

(defn save [file instructions]
  (spit file instructions))

(defn- character->file-name [character]
  (-> (int character)
      (str character-extension)))

(defn character->location [character font]
  (str font-location
       font
       path-separator
       (character->file-name character)))

(defn save-character [character pattern font]
  (save (character->location character font)
        (p/pattern->string pattern))
  (f/update-mapping! font))

(defn save-instructions-for [string file-path]
  (->> (p/string->pattern string)
       (l/string-n-pattern->layout (:valid (v/validate string)))
       (save file-path)))