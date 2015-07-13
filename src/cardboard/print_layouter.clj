(ns cardboard.print_layouter)

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
