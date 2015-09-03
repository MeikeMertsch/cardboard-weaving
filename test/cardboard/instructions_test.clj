(ns cardboard.instructions_test
  (:require [expectations :refer :all]
            [cardboard.default_chars :refer :all]
            [cardboard.pattern :refer :all]
            [cardboard.common-test-data :refer :all]
            [cardboard.instructions :refer :all]))

;;; Row Partitions
(expect [4] (pack-sizes (repeat 4 "0")))
(expect [1 2 1] (pack-sizes (concat (repeat 1 "0") (repeat 2 "1") (repeat 1 "0"))))
(expect [1 1 2] (pack-sizes (concat (repeat 1 "0") (repeat 1 "1") (repeat 2 "0"))))
(expect [3 9 2] (pack-sizes (concat (repeat 3 "0") (repeat 9 "1") (repeat 2 "0"))))

;;; Partitions To Card Pack Rows
(expect [[1 4]] (pack-sizes->instructions [4]))
(expect [[1] [2 3] [4]] (pack-sizes->instructions [1 2 1]))
(expect [[1 3] [4 12] [13 14]] (pack-sizes->instructions [3 9 2]))

;;; Instructions For Characters
(defn pattern-of [character]
  (->> (map char-patterns->matrix character)
       (apply map concat)))

(expect char-a-instructions (pattern->instructions (pattern-of [lc-a])))
(expect char-b-instructions (pattern->instructions (pattern-of [lc-b])))
(expect (concat char-a-instructions '(((1 17))) char-b-instructions)
        (pattern->instructions (pattern-of [lc-a letter-space lc-b])))

;;; Distinguish Between Uppercase Characters And Lowercase Characters
(expect false? (= (pattern->instructions (pattern-of [lc-a])) (pattern->instructions (pattern-of [uc-a]))))

;;; Turning The Pattern
(expect (list (repeat 17 "0")) (turn-pattern-by-90-degrees (repeat 17 (repeat 1 "0"))))