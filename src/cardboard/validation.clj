(ns cardboard.validation
  (:require [cardboard.string-cleaning :as sc]))

(defn validate [string]
  (if (empty? (sc/unavailable-chars string))
    {:outcome :ok
     :valid string}
    {:outcome :not-ok
     :error (sc/unavailable-chars string)
     :valid (sc/clean string)}))