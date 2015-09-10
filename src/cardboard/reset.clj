(ns cardboard.reset
  (require [cardboard.default_chars :as dc]
           [cardboard.saving :as s]
           [cardboard.constants :refer :all]))

(defn prefill-default-characters []
  (doseq [character dc/available-chars]
    (s/save (s/character->location character default-font) (dc/char->pattern character))))