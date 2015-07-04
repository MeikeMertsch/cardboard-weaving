(ns cardboard.font-interpreter-test
  (:require [expectations :refer :all]
            [cardboard.font_interpreter :refer :all]))

;----- Row Partitions
(expect [4] (partition-of "0000"))
(expect [1 2 1] (partition-of "0110"))
(expect [1 1 2] (partition-of "0100"))
(expect [3 9 2] (partition-of "00011111111100"))

;----- Partitions To Card Pack Rows
(expect ["1-4"] (card-pack-row-of [4]))
(expect ["1" "2-3" "4"] (card-pack-row-of [1 2 1]))
(expect ["1-3" "4-12" "13-14"] (card-pack-row-of [3 9 2]))

;----- Interprete Pattern Row
(expect ["1-4"] (row-of-pattern "0000"))
(expect ["1" "2-3" "4"] (row-of-pattern "0110"))
(expect ["1-3" "4-12" "13-14"] (row-of-pattern "00011111111100"))

;----- Construct Pack Descriptions Of Partitions
(expect "1-4" (pack-description [1 4]))
(expect "1" (pack-description [1]))
