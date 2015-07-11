(ns cardboard.saver-test
  (:require [expectations :refer :all]
            [cardboard.saver :refer :all]))

;----- Save Something
(expect-let [placeholder (save "instructions-saver.txt" "1-332")] "1-332" (slurp "instructions-saver.txt"))
(expect-let [placeholder (save "instructions-saver.txt" "1-333")] "1-333" (slurp "instructions-saver.txt"))
