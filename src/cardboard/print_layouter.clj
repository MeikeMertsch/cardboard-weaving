(ns cardboard.print_layouter
  (:require [cardboard.instructions :refer :all]))

(defn pattern->instructions [pattern]
  (map row-of-pattern pattern))

(defn pack-description [start-end-coll]
  (apply str (interpose "-" start-end-coll)))

(defn convert-instructions [instructions]
  (->> (map #(map pack-description %) instructions)
       (map #(clojure.string/join " " %))
       (interpose "\n")
       clojure.string/join))

(defn string-n-pattern->layout [string pattern]
  (let [instructions (pattern->instructions pattern)
        header "Pattern for" ;TODO: Put this string somewhere safe
        new-line "\n"]
    (apply str header
               new-line
               string
               new-line
               new-line
               (convert-instructions instructions))))