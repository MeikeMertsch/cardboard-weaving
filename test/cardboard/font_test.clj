(ns cardboard.font-test
  (require [expectations :refer :all]
           [cardboard.font :refer :all]
           [cardboard.constants :refer :all]
           [cardboard.default_chars :as dc]))

#_(expect-let [placeholder (dc/prefill-default-characters)] dc/available-chars (available-chars))


(expect \d (#'cardboard.font/filename->character "resources/default/100.ch" default-font))
(expect \e (#'cardboard.font/filename->character "resources/custom/101.ch" "custom"))

(expect "/" (#'cardboard.font/remove-substring "resources/default" default-character-location))

(expect ["75.ch"] (#'cardboard.font/keep-only-character-files ["75.ch" "something different"]))

(expect dc/space ((char->pattern) \space))
(expect dc/lc-a ((char->pattern) \a))

(expect ["custom" default-font] (fonts)) ;highly dependant on available fonts