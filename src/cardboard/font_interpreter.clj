(ns cardboard.font_interpreter
  (:require [expectations :refer :all]
            [cardboard.font :refer :all]))

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

(defn instructions-for [string]
  (->> (pattern-of string)
       clojure.string/split-lines
       (map clojure.string/trim)
       (map row-of-pattern)))

