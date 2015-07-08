(ns cardboard.font-test
  (:require [expectations :refer :all]
            [cardboard.font :refer :all]
            [cardboard.default_letters :refer :all]))

;----- Splitting A String
(expect '("H" "a" "g" "r" "i" "d") (str->chars "Hagrid"))
(expect '() (str->chars ""))
(expect '(" ") (str->chars " "))

;----- Pattern Of A String
(expect "00000000000000000\n00000000000000000\n00000000000000000" (pattern-of " "))

;----- Turn The Pattern Into A Matrix
(expect (repeat 17 '("0" "0" "0")) (letter-patterns->matrix space))
(expect (repeat 17 '("0")) (letter-patterns->matrix letter-space))

;----- Turn Letters By 90 Degrees
(expect "00000000000000000" (turn-pattern-90-deg letter-space))
