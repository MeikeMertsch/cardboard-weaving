(ns cardboard.font_interpreter
  (:require [expectations :refer :all]
            [cardboard.pattern :refer :all]))

(defn pack-sizes [pattern-row]
  (->> (partition-by identity pattern-row)
       (map count)))

(defn card-pack-row-of [row-partition]
  (->> (map list (map inc (reductions + (cons 0 row-partition)))
                 (reductions + row-partition))
       (map distinct)))

(defn row-of-pattern [pattern-row]
  (->> (pack-sizes pattern-row)
       card-pack-row-of))

(defn instructions-for [pattern]                             ;TODO: instead of the string give it the pattern
  (map row-of-pattern pattern))

