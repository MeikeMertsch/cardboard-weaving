(ns cardboard.font-interpreter-test
  (:require [expectations :refer :all]
            [cardboard.font_interpreter :refer :all]))

;----- Row Partitions
(expect [4] (pack-sizes "0000"))
(expect [1 2 1] (pack-sizes "0110"))
(expect [1 1 2] (pack-sizes "0100"))
(expect [3 9 2] (pack-sizes "00011111111100"))

;----- Partitions To Card Pack Rows
(expect [[1 4]] (card-pack-row-of [4]))
(expect [[1] [2 3] [4]] (card-pack-row-of [1 2 1]))
(expect [[1 3] [4 12] [13 14]] (card-pack-row-of [3 9 2]))

;----- Interprete Pattern Row
(expect [[1 4]] (row-of-pattern "0000"))
(expect [[1] [2 3] [4]] (row-of-pattern "0110"))
(expect [[1 3] [4 12] [13 14]] (row-of-pattern "00011111111100"))

;----- Instructions For A Letters
(def char-a-instructions
  '(((1 5) (6 7) (8 10) (11) (12 17))
    ((1 4) (5 8) (9 10) (11 12) (13 17))
    ((1 4) (5 6) (7) (8 9) (10) (11 12) (13 17))
    ((1 4) (5 6) (7 8) (9) (10) (11 12) (13 17))
    ((1 5) (6 12) (13 17))
    ((1 4) (5 11) (12 17))
    ((1 4) (5) (6 17))))

(def char-b-instructions
  '(((1 4) (5 15) (16 17))
    ((1 4) (5 15) (16 17))
    ((1 5) (6 7) (8 9) (10 11) (12 17))
    ((1 4) (5 6) (7 10) (11 12) (13 17))
    ((1 4) (5 7) (8 9) (10 12) (13 17))
    ((1 5) (6 11) (12 17))
    ((1 6) (7 10) (11 17))))

(expect char-a-instructions (instructions-for "a"))
(expect char-b-instructions (instructions-for "b"))
(expect (concat char-a-instructions '(((1 17))) char-b-instructions) (instructions-for "ab"))

;----- Distinguish Between Uppercase Letters And Lowercase Letters
(expect false? (= (instructions-for "a") (instructions-for "A")))
