(ns cardboard.pattern-test
  (:require [expectations :refer :all]
            [cardboard.pattern :refer :all]
            [cardboard.default_chars :refer :all]))

;----- Splitting A String
(expect '("H" "a" "g" "r" "i" "d") (str->chars "Hagrid"))
(expect '() (str->chars ""))
(expect '(" ") (str->chars " "))
(expect '(" " "5") (str->chars " 5"))

;----- Pattern Of A String
(expect (repeat 17 (repeat 3 "0")) (string->pattern " "))
(expect (repeat 17 (repeat 7 "0")) (string->pattern "  "))
(expect (repeat 17 (repeat 3 "0")) (string->pattern " 5"))

;----- Turn The Pattern Into A Matrix
(expect (repeat 17 '("0" "0" "0")) (char-patterns->matrix space))
(expect (repeat 17 '("0")) (char-patterns->matrix letter-space))

