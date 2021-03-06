(ns cardboard.pattern-test
  (:require [expectations :refer :all]
            [cardboard.pattern :refer :all]
            [cardboard.constants :as con]
            [cardboard.default_chars :refer :all]))

;;; Turn The Pattern Into A Matrix
(expect (repeat 17 '("0" "0" "0")) (#'cardboard.pattern/char-patterns->matrix space))
(expect (repeat 17 '("0")) (#'cardboard.pattern/char-patterns->matrix con/letter-space))

;;; Pattern Of A String
(expect (repeat 17 (repeat 3 "0")) (string->pattern " "))
(expect (repeat 17 (repeat 7 "0")) (string->pattern "  "))
(expect (repeat 17 (repeat 3 "0")) (string->pattern " 5"))
(expect con/empty-pattern (string->pattern ""))

;;; String Of A Pattern
(expect "12\n34\n56" (pattern->string '(("1" "2") ("3" "4") ("5" "6"))))
