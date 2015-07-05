(ns cardboard.core-test
  (:require [expectations :refer :all]
            [cardboard.core :refer :all]))

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

;----- Writing The Instructions To A File
(def char-a-string "1-5 6-7 8-10 11 12-17\n1-4 5-8 9-10 11-12 13-17\n1-4 5-6 7 8-9 10 11-12 13-17\n1-4 5-6 7-8 9 10 11-12 13-17\n1-5 6-12 13-17\n1-4 5-11 12-17\n1-4 5 6-17")
(def char-b-string "1-4 5-15 16-17\n1-4 5-15 16-17\n1-5 6-7 8-9 10-11 12-17\n1-4 5-6 7-10 11-12 13-17\n1-4 5-7 8-9 10-12 13-17\n1-5 6-11 12-17\n1-6 7-10 11-17")

(expect-let [placeholder (save-instructions-for "a")] char-a-string (slurp "instructions.txt"))
(expect-let [placeholder (save-instructions-for "b")] char-b-string (slurp "instructions.txt"))
(expect-let [placeholder (save-instructions-for "ab")] (str char-a-string "\n1-17\n" char-b-string) (slurp "instructions.txt"))

;----- Distinguish Between Uppercase Letters And Lowercase Letters
(expect false? (= (instructions-for "a") (instructions-for "A")))
