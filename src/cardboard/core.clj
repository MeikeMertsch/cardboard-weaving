(ns cardboard.core
  (:require [cardboard.pattern :as p])
  (:require [cardboard.print_layouter :as l])
  (:require [cardboard.saver :as s]))

(defn save-instructions-for [string file-path]
  (->> (p/string->pattern string)
       (l/string-n-pattern->layout string)
       (s/save file-path)))

(defn pattern-in-rows [string]
  (if (= "" string)
    '(())
    (->> (p/string->pattern string)
           (apply map concat))))