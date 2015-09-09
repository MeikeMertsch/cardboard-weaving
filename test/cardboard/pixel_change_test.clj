(ns cardboard.pixel-change-test
  (:require [expectations :refer :all]
            [cardboard.pixel-change :refer :all]
            [cardboard.constants :refer :all]))


;;; Finding The Pixel Coordinations Of The Absolute Coordinations
(expect [1 2] (#'cardboard.pixel-change/pattern-pixel-coords [50 50]))
(expect [1 0] (#'cardboard.pixel-change/pattern-pixel-coords [58 8]))


;;; Check The Coordinates
(expect false (#'cardboard.pixel-change/valid-coords? [3 0] '(("1" "2" "3") ("4" "5" "6"))))
(expect false (#'cardboard.pixel-change/valid-coords? [2 7] '(("1" "2" "3") ("4" "5" "6"))))
(expect true (#'cardboard.pixel-change/valid-coords? [0 0] '(("1" "2" "3") ("4" "5" "6"))))
(expect true (#'cardboard.pixel-change/valid-coords? [2 1] '(("1" "2" "3") ("4" "5" "6"))))


;;; Exchanging The Color Of The Pixel
(expect '(("1" "2") ("." "4") ("5" "6")) (invert-pixel [0 30] '(("1" "2") ("0" "4") ("5" "6"))))
(expect '(("0" "2" "3") ("4" "5" "6")) (invert-pixel [0 0] '(("." "2" "3") ("4" "5" "6"))))
(expect '(("1" "2" "3") ("4" "5" "6")) (invert-pixel [100 0] '(("1" "2" "3") ("4" "5" "6"))))