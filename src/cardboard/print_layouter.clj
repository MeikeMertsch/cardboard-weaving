(ns cardboard.print_layouter
  (:require [cardboard.pattern :refer :all]))

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

(defn pattern->instructions [pattern]                             ;TODO: instead of the string give it the pattern
  (map row-of-pattern pattern))




(defn pack-description [start-end-coll]
  (apply str (interpose "-" start-end-coll)))

(defn convert-instructions [instructions]
  (->> (map #(map pack-description %) instructions)
       (map #(clojure.string/join " " %))
       (interpose "\n")
       clojure.string/join))

(defn finish-layout [pattern-string instructions]
  (let [header "Pattern for" ;TODO: Put this string somewhere safe
        new-line "\n"]
    (apply str
           header
           new-line
           pattern-string
           new-line
           new-line
           (convert-instructions instructions))))