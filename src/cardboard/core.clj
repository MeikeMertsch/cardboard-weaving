(ns cardboard.core
  (:require [cardboard.default_letters :refer :all]))

(defn partition-of [pattern-row]
  (->> (partition-by identity pattern-row)
       (map count)))

(defn pack-description [start-end-coll]
  (apply str (interpose "-" start-end-coll)))

(defn card-pack-row-of [row-partition]
  (->> (map list (map inc (reductions + (cons 0 row-partition)))
                 (reductions + row-partition))
       (map distinct)
       (map pack-description)))

(defn row-of-pattern [pattern-row]
  (->> (partition-of pattern-row)
       card-pack-row-of))

(defn instructions-for [string]
  (->> (pattern-of string)
       clojure.string/split-lines
       (map clojure.string/trim)
       (map row-of-pattern)))

(defn convert-instructions [instructions]
  (->> (map #(clojure.string/join " " %) instructions)
       (interpose "\n")
       clojure.string/join))

(defn save-instructions-for [string]
  (spit "instructions.txt" (convert-instructions (instructions-for string))))
