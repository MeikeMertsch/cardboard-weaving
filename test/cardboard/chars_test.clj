(ns cardboard.chars-test
  (require [expectations :refer :all]
           [cardboard.chars :refer :all]
           [cardboard.constants :refer :all]))


(expect "d" (filename->character "resources/default/100.ch"))

(expect "/" (remove-substring "resources/default" default-character-location))

(expect ["75.ch"] (only-characters ["75.ch" "something different"]))