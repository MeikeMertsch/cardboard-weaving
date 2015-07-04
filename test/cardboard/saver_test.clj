(ns cardboard.saver-test
  (:require [expectations :refer :all]
            [cardboard.saver :refer :all]))

;----- Save Something
(expect-let [placeholder (save "1-333")] "1-333" (slurp "instructions.txt"))
