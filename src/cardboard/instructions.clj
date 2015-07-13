(ns cardboard.instructions)

(defn pack-sizes [pattern-row]
  (->> (partition-by identity pattern-row)
       (map count)))

(defn pack-sizes->instructions [pack-sizes-for-row]
  (->> (map list (map inc (reductions + (cons 0 pack-sizes-for-row)))
            (reductions + pack-sizes-for-row))
       (map distinct)))

(defn pattern->instructions [pattern]                       ;TODO: Check with James if this is worth changing
  (->> (map pack-sizes pattern)
       (map pack-sizes->instructions)))