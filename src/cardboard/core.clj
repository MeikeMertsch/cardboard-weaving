(ns cardboard.core
  (:require [cardboard.saver :refer :all])
  (:require [cardboard.font_interpreter :refer :all])
  (:require [cardboard.print_layouter :refer :all]))

(defn save-instructions-for [string file]
  (->> (instructions-for string)
       (finish-layout string)
       (save file)))

