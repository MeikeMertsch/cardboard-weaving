(ns cardboard.font_interpreter
  (:require [expectations :refer :all]))

(defn partition-of [pattern-row]
  (->> (partition-by identity pattern-row)
       (map count)))

(defn pack-description [start-end-coll]
  (apply str (interpose "-" start-end-coll)))

(defn card-pack-row-of [row-partition]
  (->> (map list (map inc (reductions + (cons 0 row-partition)))
                 (reductions + row-partition))
       (map distinct)))

(defn row-of-pattern [pattern-row]
  (->> (partition-of pattern-row)
       card-pack-row-of))

