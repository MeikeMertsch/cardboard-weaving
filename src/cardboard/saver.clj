(ns cardboard.saver)

(defn save [file instructions]
  (spit file instructions))
