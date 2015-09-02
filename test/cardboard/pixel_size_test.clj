(ns cardboard.pixel-size-test
  (require [expectations :refer :all]
           [cardboard.pixel-size :refer :all]
           [cardboard.constants :refer :all]))

;;; Sizes In Pixels
(expect [3 17] (pattern-size " "))
(expect [22 17] (pattern-size "abc"))

;;; Absolute Sizes
(expect [330 :by 390] (screen-size zoom-size "   "))
(expect [21 :by 84] (screen-size preview-size "a"))

;;; Minimum Width Is Six Pixels
(expect [180 :by 390] (screen-size zoom-size " "))

;;; Minimum Height Is Status Bar + Button Panel
(expect [0 :by (+ status-bar-height button-bar-height)] (screen-size {:width 0 :height 0} "irrelephant"))