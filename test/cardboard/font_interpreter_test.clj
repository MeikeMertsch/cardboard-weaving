(ns cardboard.font-interpreter-test
  (:require [expectations :refer :all]
            [cardboard.font_interpreter :refer :all]))

;----- Row Partitions
(expect [4] (partition-of "0000"))
(expect [1 2 1] (partition-of "0110"))
(expect [1 1 2] (partition-of "0100"))
(expect [3 9 2] (partition-of "00011111111100"))
