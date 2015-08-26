(ns cardboard.saving)

(defn save [file instructions]
  (spit file instructions))
