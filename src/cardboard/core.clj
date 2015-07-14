(ns cardboard.core
  (:require [cardboard.saver :refer :all])
  (:require [cardboard.pattern :refer :all])
  (:require [cardboard.print_layouter :refer :all]))

(defn save-instructions-for [string file-path]
  (->> (string->pattern string)
       (string-n-pattern->layout string)
       (save file-path)))

(defn pattern-in-rows [string]
  (->> (string->pattern string)
       (apply map concat)))
