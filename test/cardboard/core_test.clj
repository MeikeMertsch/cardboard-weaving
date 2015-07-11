(ns cardboard.core-test
  (:require [expectations :refer :all]
            [cardboard.core :refer :all]
            [cardboard.layouter :refer :all]
            [cardboard.common-test-data :refer :all]))

;----- Writing The Instructions To A File
(expect-let [placeholder (save-instructions-for "a")] (finish-layout "a" char-a-instructions) (slurp "instructions.txt"))
(expect-let [placeholder (save-instructions-for "b")] (finish-layout "b" char-b-instructions) (slurp "instructions.txt"))
(expect-let [placeholder (save-instructions-for "ab")] (finish-layout "ab" (concat char-a-instructions '(((1 17))) char-b-instructions)) (slurp "instructions.txt"))

;----- Writing The Instructions To A Specified File
(expect-let [placeholder (save-instructions-for "a" "instructions-new.txt")] (finish-layout "a" char-a-instructions) (slurp "instructions-new.txt"))
(expect-let [placeholder (save-instructions-for "ab" "instructions-new.txt")] (finish-layout "ab" (concat char-a-instructions '(((1 17))) char-b-instructions)) (slurp "instructions-new.txt"))
