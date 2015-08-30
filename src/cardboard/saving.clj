(ns cardboard.saving
  (:require [cardboard.constants :refer :all]
            [cardboard.pattern :as pat]))

(defn save [file instructions]
  (spit file instructions))

(defn character-file-name [character]
  (first (map int character)))

(defn save-character [character pattern]
  (save (str default-character-location
             (character-file-name character)
             default-extension)
        (pat/pattern->string pattern)))