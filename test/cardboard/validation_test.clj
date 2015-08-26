(ns cardboard.validation-test
  (require [expectations :refer :all]
           [cardboard.validation :refer :all]))

;----- Validation
(expect :ok (:outcome (validate "aB c")))
(expect :not-ok (:outcome (validate "1")))
(expect ["2" "1"] (:error (validate "ab2c1")))
(expect ["3" "1"] (:error (validate "ab3c11")))
(expect "abc" (:valid (validate "ab3c11")))
