(ns cardboard.saving-test
  (:require [expectations :refer :all]
            [cardboard.saving :refer :all]))

(def saver-test-file "instructions-saver.txt")
(def some-test-data "12345")
(def other-test-data "56789")

;;; Save Something
(expect-let [placeholder (save saver-test-file some-test-data)] some-test-data (slurp saver-test-file))
(expect-let [placeholder (save saver-test-file other-test-data)] other-test-data (slurp saver-test-file))

;;; Figure Proper Filename For Saving Characters
(expect 71 (character-file-name "G"))
(expect 32 (character-file-name " "))
(expect 117 (character-file-name "u"))