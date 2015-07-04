(ns cardboard.core-test
  (:require [expectations :refer :all]
            [cardboard.core :refer :all]))

;----- Row Partitions
(expect [4] (partition-of "0000"))
(expect [1 2 1] (partition-of "0110"))
(expect [1 1 2] (partition-of "0100"))
(expect [3 9 2] (partition-of "00011111111100"))

;----- Construct Pack Descriptions Of Partitions
(expect "1-4" (pack-description [1 4]))
(expect "1" (pack-description [1]))

;----- Partitions To Card Pack Rows
(expect ["1-4"] (card-pack-row-of [4]))
(expect ["1" "2-3" "4"] (card-pack-row-of [1 2 1]))
(expect ["1-3" "4-12" "13-14"] (card-pack-row-of [3 9 2]))

;----- Interprete Pattern Row
(expect ["1-4"] (row-of-pattern "0000"))
(expect ["1" "2-3" "4"] (row-of-pattern "0110"))
(expect ["1-3" "4-12" "13-14"] (row-of-pattern "00011111111100"))

;----- Instructions For A Letters
(def char-a-instructions
  '(("1-12" "13" "14-17")
    ("1-6" "7-13" "14-17")
    ("1-5" "6-12" "13-17")
    ("1-5" "6-7" "8" "9" "10-11" "12-13" "14-17")
    ("1-5" "6-7" "8" "9-10" "11" "12-13" "14-17")
    ("1-5" "6-7" "8-9" "10-13" "14-17")
    ("1-6" "7" "8-10" "11-12" "13-17")))

(def char-b-instructions
  '(("1-3" "4-6" "7" "8-11" "12-14")
   ("1-2" "3-4" "5-6" "7-8" "9-10" "11-12" "13-14")
   ("1-2" "3-4" "5-6" "7-8" "9-10" "11-12" "13-14")
   ("1-2" "3-12" "13-14")))

(expect char-a-instructions (instructions-for "a"))
(expect char-b-instructions (instructions-for "b"))
(expect (concat char-a-instructions '(("1-14")) char-b-instructions) (instructions-for "ab"))

;----- Converting Instructions To String
(def char-a-string "1-12 13 14-17\n1-6 7-13 14-17\n1-5 6-12 13-17\n1-5 6-7 8 9 10-11 12-13 14-17\n1-5 6-7 8 9-10 11 12-13 14-17\n1-5 6-7 8-9 10-13 14-17\n1-6 7 8-10 11-12 13-17")
(expect char-a-string (convert-instructions (instructions-for "a")))

;----- Writing The Instructions To A File
(def char-b-string "1-3 4-6 7 8-11 12-14\n1-2 3-4 5-6 7-8 9-10 11-12 13-14\n1-2 3-4 5-6 7-8 9-10 11-12 13-14\n1-2 3-12 13-14")

(expect-let [placeholder (save-instructions-for "a")] char-a-string (slurp "instructions.txt"))
(expect-let [placeholder (save-instructions-for "b")] char-b-string (slurp "instructions.txt"))
(expect-let [placeholder (save-instructions-for "ab")] (str char-a-string "\n1-14\n" char-b-string) (slurp "instructions.txt"))

;----- Distinguish Between Uppercase Letters And Lowercase Letters
(instructions-for "a")
(instructions-for "A")
(expect false? (= (instructions-for "a") (instructions-for "A")))
