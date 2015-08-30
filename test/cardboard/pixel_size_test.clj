(ns cardboard.pixel-size-test
  (require [expectations :refer :all]
           [cardboard.pixel-size :refer :all]))

(expect [3 17] (size " "))
(expect [22 17] (size "abc"))
