(ns cardboard.saver)

(defn save [instructions]
  (spit "instructions.txt" instructions))
