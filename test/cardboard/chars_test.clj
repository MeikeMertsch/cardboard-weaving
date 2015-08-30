(ns cardboard.chars-test
  (require [expectations :refer :all]
           [cardboard.constants :refer :all]
           [cardboard.default_chars :as dc]))


;;; Figure Out All Available Chars
(def filereader (clojure.java.io/file "resources/default"))

(defn only-characters [file-list]
  (filter #(.endsWith % character-extension) file-list))

(defn character-files []
  (->> (file-seq filereader)
       (map str)
       only-characters))


(defn remove [subs string]
  (clojure.string/replace string subs ""))

(defn filename->character [string]
  (->> string
       (remove character-extension)
       (remove default-character-location)
       bigint
       char
       str))

(defn char->pattern []
  (zipmap (map filename->character (character-files))
          (map slurp (character-files))))

(defn available-chars []
  (into #{} (keys (char->pattern))))

(expect dc/available-chars (available-chars))

(expect "/" (remove "resources/default" default-character-location))