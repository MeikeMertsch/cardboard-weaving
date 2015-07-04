(ns cardboard.core-test
  (:require [expectations :refer :all]
            [cardboard.core :refer :all]))

;----- Instructions For A Letters
(def char-a-instructions
  '(((1 12) (13) (14 17))
    ((1 6) (7 13) (14 17))
    ((1 5) (6 12) (13 17))
    ((1 5) (6 7) (8) (9) (10 11) (12 13) (14 17))
    ((1 5) (6 7) (8) (9 10) (11) (12 13) (14 17))
    ((1 5) (6 7) (8 9) (10 13) (14 17))
    ((1 6) (7) (8 10) (11 12) (13 17))))

(def char-b-instructions
  '(((1 7) (8 11) (12 17))
    ((1 6) (7 12) (13 17))
    ((1 5) (6 8) (9 10) (11 13) (14 17))
    ((1 5) (6 7) (8 11) (12 13) (14 17))
    ((1 6) (7 8) (9 11) (12) (13 17))
    ((1 2) (3 13) (14 17))
    ((1 2) (3 13) (14 17))))

(expect char-a-instructions (instructions-for "a"))
(expect char-b-instructions (instructions-for "b"))
(expect (concat char-a-instructions '(((1 14))) char-b-instructions) (instructions-for "ab"))

;----- Writing The Instructions To A File
(def char-a-string "1-12 13 14-17\n1-6 7-13 14-17\n1-5 6-12 13-17\n1-5 6-7 8 9 10-11 12-13 14-17\n1-5 6-7 8 9-10 11 12-13 14-17\n1-5 6-7 8-9 10-13 14-17\n1-6 7 8-10 11-12 13-17")
(def char-b-string "1-7 8-11 12-17\n1-6 7-12 13-17\n1-5 6-8 9-10 11-13 14-17\n1-5 6-7 8-11 12-13 14-17\n1-6 7-8 9-11 12 13-17\n1-2 3-13 14-17\n1-2 3-13 14-17")

(expect-let [placeholder (save-instructions-for "a")] char-a-string (slurp "instructions.txt"))
(expect-let [placeholder (save-instructions-for "b")] char-b-string (slurp "instructions.txt"))
(expect-let [placeholder (save-instructions-for "ab")] (str char-a-string "\n1-14\n" char-b-string) (slurp "instructions.txt"))

;----- Distinguish Between Uppercase Letters And Lowercase Letters
(expect false? (= (instructions-for "a") (instructions-for "A")))
