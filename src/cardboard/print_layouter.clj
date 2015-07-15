(ns cardboard.print_layouter
  (:require [cardboard.instructions :as i]))

(defn pack-description [start-end-coll]
  (apply str (interpose "-" start-end-coll)))

(defn convert-instructions [instructions]                    ;TODO: Look through with James
  (->> (map #(map pack-description %) instructions)
       (map #(clojure.string/join " " %))
       (interpose "\n")
       clojure.string/join))

(defn compose-layout [string instructions]
  (let [header "Pattern for" ;TODO: Put this string somewhere safe
        new-line "\n"]
    (apply str header
           new-line
           string
           new-line
           new-line
           (convert-instructions instructions))))

(defn string-n-pattern->layout [string pattern]
  (->> (i/pattern->instructions pattern)
       (compose-layout string)))
