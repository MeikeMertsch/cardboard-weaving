(ns cardboard.print_layouter
  (:require [cardboard.instructions :as i])
  (:require [cardboard.constants :refer :all]))

(defn pack-description [start-end-coll]
  (apply str (interpose dash start-end-coll)))

(defn convert-instructions [instructions]                    ;TODO: Look through with James
  (->> (map #(map pack-description %) instructions)
       (map #(clojure.string/join space %))
       (interpose new-line)
       clojure.string/join))

(defn compose-layout [string instructions]
  (apply str header
             new-line
             string
             new-line
             new-line
             (convert-instructions instructions)))

(defn string-n-pattern->layout [string pattern]
  (->> (i/pattern->instructions pattern)
       (compose-layout string)))
