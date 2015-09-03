(ns cardboard.pixel-change-test
  (:require [expectations :refer :all]
            [cardboard.pixel-change :refer :all]
            [cardboard.constants :refer :all]))


;;; Finding The Pixel Coordinations Of The Absolute Coordinations
(expect [1 2] (pattern-pixel-coords [40 40]))
(expect [1 0] (pattern-pixel-coords [58 8]))


;;; Check The Coordinates
(expect false (valid-coords? [3 0] '(("1" "2" "3") ("4" "5" "6"))))
(expect false (valid-coords? [2 7] '(("1" "2" "3") ("4" "5" "6"))))
(expect true (valid-coords? [0 0] '(("1" "2" "3") ("4" "5" "6"))))
(expect true (valid-coords? [2 1] '(("1" "2" "3") ("4" "5" "6"))))


;;; Exchanging The Color Of The Pixel
(expect '(("1" "2") ("." "4") ("5" "6")) (invert-pixel [0 20] '(("1" "2") ("0" "4") ("5" "6"))))
(expect '(("0" "2" "3") ("4" "5" "6")) (invert-pixel [0 0] '(("." "2" "3") ("4" "5" "6"))))
(expect '(("1" "2" "3") ("4" "5" "6")) (invert-pixel [100 0] '(("1" "2" "3") ("4" "5" "6"))))