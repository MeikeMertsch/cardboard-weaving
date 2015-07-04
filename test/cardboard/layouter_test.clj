(ns cardboard.layouter-test
  (:require [expectations :refer :all]
            [cardboard.layouter :refer :all]))

;----- Construct Pack Descriptions Of Partitions
(expect "1-4" (pack-description [1 4]))
(expect "1" (pack-description [1]))

;----- Converting Instructions To String
(def char-a-string "1-12 13 14-17\n1-6 7-13 14-17")
(expect char-a-string (convert-instructions '(((1 12) (13) (14 17)) ((1 6) (7 13) (14 17)))))
