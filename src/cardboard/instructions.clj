(ns cardboard.instructions)
;TODO: naming

(defn pack-sizes [pattern-row]
  (->> (partition-by identity pattern-row)
       (map count)))

(defn card-pack-row-of [pack-sizes-for-row]
  (->> (map list (map inc (reductions + (cons 0 pack-sizes-for-row)))
            (reductions + pack-sizes-for-row))
       (map distinct)))

(defn row-of-pattern [pattern-row]
  (->> (pack-sizes pattern-row)
       card-pack-row-of))


