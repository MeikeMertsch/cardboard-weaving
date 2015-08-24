(ns cardboard.input-test
  (:require [expectations :refer :all]
            [cardboard.input :refer :all]
            [cardboard.core :as cc]
            [cardboard.preview-canvas :as pre]
            [cardboard.constants :refer :all]))

;----- Ensure Correct File Extension
(expect "Hagrid.txt" (guarantee-txt "Hagrid"))
(expect "Meike.txt" (guarantee-txt "Meike.txt"))
(expect "Wir.tx.txt" (guarantee-txt "Wir.tx"))


;----- Preview Shall Be Called Correctly
(def space-in-rows
  [[(repeat 17 (repeat 3 "0"))]])

(expect space-in-rows
        (side-effects [pre/preview]
                      (preview-new-string " ")))


;----- Saves With Proper Extension
(def the-string "abc")

(expect [[the-string "test_Hagrid II.txt"]]
        (side-effects [cc/save-instructions-for]
                      (save-instructions the-string false "test_Hagrid II")))

(expect [[the-string "test_Meike II.txt"]]
        (side-effects [cc/save-instructions-for]
                      (save-instructions the-string false "test_Meike II.txt")))


;----- Generate A Beautiful Error
(expect (str invalid-characters "1, =") (error-message-for ["1" "="]))