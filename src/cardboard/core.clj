(ns cardboard.core
  (:require [cardboard.saver :refer :all])
  (:require [cardboard.pattern :refer :all])
  (:require [cardboard.print_layouter :refer :all]))

(defn save-instructions-for [string file]
  (->> (string->pattern string)
       (pattern->instructions)
       (string-n-instructions->layout string)
       (save file)))

