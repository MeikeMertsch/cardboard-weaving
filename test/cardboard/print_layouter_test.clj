(ns cardboard.print-layouter-test
  (:require [expectations :refer :all]
            [cardboard.print_layouter :refer :all]))

;----- Construct Pack Descriptions Of Partitions
(expect "1-4" (pack-description [1 4]))
(expect "1" (pack-description [1]))

;----- Converting Instructions To String
(def char-a-string "1-12 13 14-17\n1-6 7-13 14-17")
(expect char-a-string (convert-instructions '(((1 12) (13) (14 17)) ((1 6) (7 13) (14 17)))))

;----- Finish Layout For Saving
(def header "Pattern for\n")
(def pattern-string "Example\n\n")
(expect (str header pattern-string char-a-string) (finish-layout "Example" '(((1 12) (13) (14 17)) ((1 6) (7 13) (14 17)))))
