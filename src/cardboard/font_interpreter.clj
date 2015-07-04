(ns cardboard.font_interpreter
  (:require [expectations :refer :all]))

(defn partition-of [pattern-row]
  (->> (partition-by identity pattern-row)
       (map count)))

