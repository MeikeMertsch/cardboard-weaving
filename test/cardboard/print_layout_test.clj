(ns cardboard.print-layout-test
  (:require [expectations :refer :all]
            [cardboard.common-test-data :refer :all]
            [cardboard.pattern :refer :all]
            [cardboard.print_layout :refer :all]))

;----- Construct Pack Descriptions Of Partitions
(expect "1-4" (#'cardboard.print_layout/pack-description [1 4]))
(expect "1" (#'cardboard.print_layout/pack-description [1]))

;----- Converting Instructions To String
(def char-a-string "1-12 13 14-17\n1-6 7-13 14-17")
(expect char-a-string (#'cardboard.print_layout/convert-instructions '(((1 12) (13) (14 17)) ((1 6) (7 13) (14 17)))))

;----- Finish Layout For Saving
(def header "Pattern for\n")
(def pattern-string "Example\n\n")
(def char-l-string "1-4 5-15 16-17\n1-4 5-15 16-17")
(expect (str header pattern-string char-l-string) (string-n-pattern->layout "Example" (string->pattern "l")))

