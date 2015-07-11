(ns cardboard.core-test
  (:require [expectations :refer :all]
            [cardboard.core :refer :all]
            [cardboard.layouter :refer :all]
            [cardboard.common-test-data :refer :all]))

(def core-test-file "instructions-new.txt")

;----- Writing The Instructions To A Specified File
(expect-let [placeholder (save-instructions-for "a" core-test-file)] (finish-layout "a" char-a-instructions) (slurp core-test-file))
(expect-let [placeholder (save-instructions-for "b" core-test-file)] (finish-layout "b" char-b-instructions) (slurp core-test-file))
(expect-let [placeholder (save-instructions-for "ab" core-test-file)] (finish-layout "ab" (concat char-a-instructions '(((1 17))) char-b-instructions)) (slurp core-test-file))
