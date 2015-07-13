(ns cardboard.pattern-test
  (:require [expectations :refer :all]
            [cardboard.pattern :refer :all]
            [cardboard.default_letters :refer :all]))

;----- Splitting A String
(expect '("H" "a" "g" "r" "i" "d") (str->chars "Hagrid"))
(expect '() (str->chars ""))
(expect '(" ") (str->chars " "))

;----- Pattern Of A String
(expect (list (repeat 17 (repeat 3 "0"))) (string->pattern " "))
(expect (list (repeat 17 (repeat 3 "0")) (repeat 17 (repeat 1 "0")) (repeat 17 (repeat 3 "0"))) (string->pattern "  "))

;----- Turn The Pattern Into A Matrix
(expect (repeat 17 '("0" "0" "0")) (letter-patterns->matrix space))
(expect (repeat 17 '("0")) (letter-patterns->matrix letter-space))