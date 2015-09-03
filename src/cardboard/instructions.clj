(ns cardboard.instructions)

(defn turn-pattern-by-90-degrees [pattern]
  (->> (apply map list pattern)
       (map reverse)))

(defn pack-sizes [pattern-row]
  (->> (partition-by identity pattern-row)
       (map count)))

(defn pack-sizes->instructions [pack-sizes-for-row]
  (->> (map list (map inc (reductions + 0 pack-sizes-for-row))
            (reductions + pack-sizes-for-row))
       (map distinct)))

(defn pattern->instructions [pattern]
  (->> (turn-pattern-by-90-degrees pattern)
       (map pack-sizes)
       (map pack-sizes->instructions)))
