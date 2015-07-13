(ns cardboard.core
  (:require [cardboard.saver :refer :all])
  (:require [cardboard.pattern :refer :all])
  (:require [cardboard.print_layouter :refer :all]))

(defn save-instructions-for [string file]
  (->> (pattern-of string)
       (pattern->instructions)
       (finish-layout string)
       (save file)))

