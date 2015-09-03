(ns cardboard.validation
  (:require [cardboard.string-cleaning :as sc]))

(defn validate [string]
  (if (empty? (sc/unavailable-chars (seq string)))
    {:outcome :ok
     :valid string}
    {:outcome :not-ok
     :error (sc/unavailable-chars (seq string))
     :valid (sc/clean string)}))