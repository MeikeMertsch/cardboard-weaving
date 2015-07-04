(ns cardboard.layouter-test
  (:require [expectations :refer :all]
            [cardboard.layouter :refer :all]))

;----- Construct Pack Descriptions Of Partitions
(expect "1-4" (pack-description [1 4]))
(expect "1" (pack-description [1]))
