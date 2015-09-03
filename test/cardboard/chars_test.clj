(ns cardboard.chars-test
  (require [expectations :refer :all]
           [cardboard.chars :refer :all]
           [cardboard.constants :refer :all]
           [cardboard.default_chars :as dc]))

#_(expect-let [placeholder (dc/prefill-default-characters)] dc/available-chars (available-chars))

(expect \d (filename->character "resources/default/100.ch"))

(expect "/" (remove-substring "resources/default" default-character-location))

(expect ["75.ch"] (keep-only-character-files ["75.ch" "something different"]))

(expect dc/space ((char->pattern) \space))
(expect dc/lc-a ((char->pattern) \a))