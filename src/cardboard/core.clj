(ns cardboard.core
  (:require [cardboard.saver :refer :all])
  (:require [cardboard.font_interpreter :refer :all])
  (:require [cardboard.font :refer :all])
  (:require [cardboard.layouter :refer :all]))

(defn instructions-for [string]
  (->> (pattern-of string)
       clojure.string/split-lines
       (map clojure.string/trim)
       (map row-of-pattern)))

(defn save-instructions-for [string]
  (save (finish-layout string (instructions-for string))))
