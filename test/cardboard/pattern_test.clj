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
(expect (repeat 17 '("0" "0" "0")) (letter-patterns->matrix space))
(expect (repeat 17 '("0")) (letter-patterns->matrix letter-space))

;----- Validation
(expect :ok (:outcome (validate "aB c")))
(expect :not-ok (:outcome (validate "1")))
(expect ["2" "1"] (:error (validate "ab2c1")))
(expect ["3" "1"] (:error (validate "ab3c11")))
(expect "abc" (:valid (validate "ab3c11")))

;----- Clean Strings
(expect "clean string" (clean "clean string"))
(expect "string" (clean "%s2tr5ing2"))