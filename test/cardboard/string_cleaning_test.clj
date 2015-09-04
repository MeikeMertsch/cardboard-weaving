(ns cardboard.string-cleaning-test
  (:require [expectations :refer :all]
            [cardboard.string-cleaning :refer :all]
            [cardboard.default_chars :as dc]))

;----- Clean Strings
(expect "clean string" (clean "clean string"))
(expect "string" (clean "%s2tr5ing2"))