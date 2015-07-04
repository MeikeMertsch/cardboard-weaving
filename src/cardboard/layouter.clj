(ns cardboard.layouter)

(defn pack-description [start-end-coll]
  (apply str (interpose "-" start-end-coll)))
