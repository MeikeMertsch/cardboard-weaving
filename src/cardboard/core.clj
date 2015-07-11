(ns cardboard.core
  (:require [cardboard.saver :refer :all])
  (:require [cardboard.font_interpreter :refer :all])
  (:require [cardboard.layouter :refer :all]))

(defn save-instructions-for
  ([string] (save-instructions-for string "instructions.txt")) ;TODO: Get rid of this logic again as not needed anymore.
  ([string file] (->> (instructions-for string)
                      (finish-layout string)
                      (save file))))

