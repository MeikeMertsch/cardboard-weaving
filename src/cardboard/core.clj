(ns cardboard.core
  (:require [cardboard.saver :refer :all])
  (:require [cardboard.font_interpreter :refer :all])
  (:require [cardboard.layouter :refer :all]))

(defn save-instructions-for [string]
  (->> (instructions-for string)
       (finish-layout string)
       save))
