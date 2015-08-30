(ns cardboard.saving
  (:require [cardboard.constants :refer :all]))

(defn save [file instructions]
  (spit file instructions))

