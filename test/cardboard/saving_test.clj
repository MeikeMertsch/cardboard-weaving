(ns cardboard.saving-test
  (:require [expectations :refer :all]
            [cardboard.saving :refer :all]
            [cardboard.print_layout :refer :all]
            [cardboard.pattern :as p]))

(def saver-test-file "instructions-saver.txt")
(def some-test-data "12345")
(def other-test-data "56789")

;;; Save Something
(expect-let [placeholder (save saver-test-file some-test-data)] some-test-data (slurp saver-test-file))
(expect-let [placeholder (save saver-test-file other-test-data)] other-test-data (slurp saver-test-file))

;;; Figure Proper Filename For Saving Characters
(expect "71.ch" (#'cardboard.saving/character->file-name \G))
(expect "32.ch" (#'cardboard.saving/character->file-name \space))
(expect "117.ch" (#'cardboard.saving/character->file-name \u))

(def core-test-file "instructions-new.txt")
(def placeholder)

;;; Writing The Instructions To A Specified File
(expect-let [placeholder (save-instructions-for "a" core-test-file)] (#(string-n-pattern->layout % (p/string->pattern %)) "a") (slurp core-test-file))
(expect-let [placeholder (save-instructions-for "b" core-test-file)] (#(string-n-pattern->layout % (p/string->pattern %)) "b") (slurp core-test-file))
(expect-let [placeholder (save-instructions-for "ab" core-test-file)] (#(string-n-pattern->layout % (p/string->pattern %)) "ab") (slurp core-test-file))