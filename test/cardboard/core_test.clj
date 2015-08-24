(ns cardboard.core-test
  (:require [expectations :refer :all]
            [cardboard.core :refer :all]
            [cardboard.print_layouter :refer :all]
            [cardboard.pattern :as p]))

(def core-test-file "instructions-new.txt")
(def placeholder)

;----- Writing The Instructions To A Specified File
(expect-let [placeholder (save-instructions-for "a" core-test-file)] (#(string-n-pattern->layout % (p/string->pattern %)) "a") (slurp core-test-file))
(expect-let [placeholder (save-instructions-for "b" core-test-file)] (#(string-n-pattern->layout % (p/string->pattern %)) "b") (slurp core-test-file))
(expect-let [placeholder (save-instructions-for "ab" core-test-file)] (#(string-n-pattern->layout % (p/string->pattern %)) "ab") (slurp core-test-file))

;----- Give Me The Pattern To A String
(expect (repeat 17 (repeat 7 "0")) (pattern-in-rows "  "))
(expect '(()) (pattern-in-rows ""))