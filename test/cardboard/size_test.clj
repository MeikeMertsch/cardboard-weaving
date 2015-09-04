(ns cardboard.size-test
  (require [expectations :refer :all]
           [cardboard.size :refer :all]
           [cardboard.constants :refer :all]
           [cardboard.pattern :as pat]))

;;; Sizes In Pixels
(expect [3 17] (pattern-size (pat/string->pattern " ")))
(expect [22 17] (pattern-size (pat/string->pattern "abc")))

;;; Absolute Sizes
(expect [341 :by 357] (screen-size zoom-size "   "))
(expect [28 :by 51] (screen-size preview-size "a"))

;;; Minimum Width Is Six Pixels
(expect [186 :by 357] (screen-size zoom-size " "))

;;; Minimum Height Is Status Bar + Button Panel
(expect [0 :by 0] (screen-size {:width 0 :height 0} "irrelephant"))