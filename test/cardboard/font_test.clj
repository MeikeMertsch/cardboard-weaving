(ns cardboard.font-test
  (require [expectations :refer :all]
           [cardboard.font :refer :all]
           [cardboard.constants :refer :all]
           [cardboard.default_chars :as dc]))

#_(expect-let [placeholder (dc/prefill-default-characters)] dc/available-chars (available-chars))

(expect \d (filename->character "resources/default/100.ch" "default"))
(expect \e (filename->character "resources/custom/101.ch" "custom"))

(expect "/" (remove-substring "resources/default" default-character-location))

(expect ["75.ch"] (keep-only-character-files ["75.ch" "something different"]))

(expect dc/space ((char->pattern) \space))
(expect dc/lc-a ((char->pattern) \a))



(defn reader [dir]
  (clojure.java.io/file dir))

(defn keep-only-directories [files]
  (filter #(.isDirectory %) files))

(defn directories [dir]
  (->> (file-seq (reader dir))
       keep-only-directories
       (map str)
       (map (partial remove-substring dir))
       (remove (partial = (remove-substring "/" dir)))))

(expect ["custom" "default"] (directories "resources/"))