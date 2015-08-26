(ns cardboard.string-cleaner-test
  (:require [expectations :refer :all]
            [cardboard.string-cleaner :refer :all]))

;----- Clean Strings
(expect "clean string" (clean "clean string"))
(expect "string" (clean "%s2tr5ing2"))