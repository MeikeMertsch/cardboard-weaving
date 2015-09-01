(ns cardboard.saving
  (:require [cardboard.constants :refer :all]))

(defn save [file instructions]
  (spit file instructions))

(defn character-file-name [character]
  (-> (int (first character))
      (str  character-extension)))

(defn character-location [character]
  (str default-character-location
       (character-file-name character)))
