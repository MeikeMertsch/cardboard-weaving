(ns cardboard.layouter)

(defn pack-description [start-end-coll]
  (apply str (interpose "-" start-end-coll)))

(defn convert-instructions [instructions]
  (->> (map #(map pack-description %) instructions)
       (map #(clojure.string/join " " %))
       (interpose "\n")
       clojure.string/join))
