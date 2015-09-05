(ns cardboard.reset
  (require [cardboard.default_chars :as dc]
           [cardboard.saving :as s]))

(defn prefill-default-characters []
    (doseq [character dc/available-chars]
      (s/save (s/character->location character) (dc/char->pattern character))))