(ns cardboard.print-layouter-test
  (:require [expectations :refer :all]
            [cardboard.pattern :refer :all]
            [cardboard.common-test-data :refer :all]
            [cardboard.print_layouter :refer :all]))

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
(expect char-a-instructions (pattern->instructions (string->pattern "a")))
(expect char-b-instructions (pattern->instructions (string->pattern "b")))
(expect (concat char-a-instructions '(((1 17))) char-b-instructions) (pattern->instructions (string->pattern "ab")))

;----- Distinguish Between Uppercase Letters And Lowercase Letters
(expect false? (= (pattern->instructions (string->pattern "a")) (pattern->instructions (string->pattern "A"))))




;----- Construct Pack Descriptions Of Partitions
(expect "1-4" (pack-description [1 4]))
(expect "1" (pack-description [1]))

;----- Converting Instructions To String
(def char-a-string "1-12 13 14-17\n1-6 7-13 14-17")
(expect char-a-string (convert-instructions '(((1 12) (13) (14 17)) ((1 6) (7 13) (14 17)))))

;----- Finish Layout For Saving
(def header "Pattern for\n")
(def pattern-string "Example\n\n")
(expect (str header pattern-string char-a-string) (string-n-instructions->layout "Example" '(((1 12) (13) (14 17)) ((1 6) (7 13) (14 17)))))
