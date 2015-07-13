(ns cardboard.font-interpreter-test
  (:require [expectations :refer :all]
            [cardboard.font_interpreter :refer :all]
            [cardboard.pattern :refer :all]
            [cardboard.common-test-data :refer :all]))

;----- Row Partitions
(expect [4] (pack-sizes (repeat 4 "0")))
(expect [1 2 1] (pack-sizes (concat (repeat 1 "0") (repeat 2 "1") (repeat 1 "0"))))
(expect [1 1 2] (pack-sizes (concat (repeat 1 "0") (repeat 1 "1") (repeat 2 "0"))))
(expect [3 9 2] (pack-sizes (concat (repeat 3 "0") (repeat 9 "1") (repeat 2 "0"))))

;----- Partitions To Card Pack Rows
(expect [[1 4]] (card-pack-row-of [4]))
(expect [[1] [2 3] [4]] (card-pack-row-of [1 2 1]))
(expect [[1 3] [4 12] [13 14]] (card-pack-row-of [3 9 2]))

;----- Interprete Pattern Row
(expect [[1 4]] (row-of-pattern (repeat 4 "0")))
(expect [[1] [2 3] [4]] (row-of-pattern (concat (repeat 1 "0") (repeat 2 "1") (repeat 1 "0"))))
(expect [[1 3] [4 12] [13 14]] (row-of-pattern (concat (repeat 3 "0") (repeat 9 "1") (repeat 2 "0"))))

;----- Instructions For Letters
(expect char-a-instructions (instructions-for (pattern-of "a")))
(expect char-b-instructions (instructions-for (pattern-of "b")))
(expect (concat char-a-instructions '(((1 17))) char-b-instructions) (instructions-for (pattern-of "ab")))

;----- Distinguish Between Uppercase Letters And Lowercase Letters
(expect false? (= (instructions-for (pattern-of "a")) (instructions-for (pattern-of "A"))))
