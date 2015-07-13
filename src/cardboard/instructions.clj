(ns cardboard.instructions)

(defn turn-pattern [letter]
  (->> (apply map list letter)
       (map reverse)))

(defn pack-sizes [pattern-row]
  (->> (partition-by identity pattern-row)
       (map count)))

(defn pack-sizes->instructions [pack-sizes-for-row] ;TODO: Check with James if this is worth changing
  (->> (map list (map inc (reductions + (cons 0 pack-sizes-for-row)))
            (reductions + pack-sizes-for-row))
       (map distinct)))

(defn pattern->instructions [pattern]
  (->> (map turn-pattern pattern)
       (map #(->> (map pack-sizes %)
                  (map pack-sizes->instructions)))
       (apply concat)))
