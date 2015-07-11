(ns cardboard.core-test
  (:require [expectations :refer :all]
            [cardboard.core :refer :all]
            [cardboard.layouter :refer :all]
            [cardboard.common-test-data :refer :all]))

(def default-file "instructions.txt")
(def some-other-test-file "instructions-new.txt")

;----- Writing The Instructions To A File
(expect-let [placeholder (save-instructions-for "a")] (finish-layout "a" char-a-instructions) (slurp default-file))
(expect-let [placeholder (save-instructions-for "b")] (finish-layout "b" char-b-instructions) (slurp default-file))
(expect-let [placeholder (save-instructions-for "ab")] (finish-layout "ab" (concat char-a-instructions '(((1 17))) char-b-instructions)) (slurp default-file))

;----- Writing The Instructions To A Specified File
(expect-let [placeholder (save-instructions-for "a" some-other-test-file)] (finish-layout "a" char-a-instructions) (slurp some-other-test-file))
(expect-let [placeholder (save-instructions-for "ab" some-other-test-file)] (finish-layout "ab" (concat char-a-instructions '(((1 17))) char-b-instructions)) (slurp some-other-test-file))
