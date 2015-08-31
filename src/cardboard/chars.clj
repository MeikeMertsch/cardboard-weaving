(ns cardboard.chars
  (require [cardboard.constants :refer :all]
           [cardboard.default_chars :as dc]))

(def filereader (clojure.java.io/file "resources/default"))

(defn only-characters [file-list]
  (filter #(.endsWith % character-extension) file-list))

(defn character-files []
  (->> (file-seq filereader)
       (map str)
       only-characters))

(defn remove-substring [subs string]
  (clojure.string/replace string subs ""))

(defn filename->character [string]
  (->> string
       (remove-substring character-extension)
       (remove-substring default-character-location)
       bigint
       char
       str))

(defn letter-space []
  dc/letter-space)

(defn char->pattern []
  (zipmap (map filename->character (character-files))
          (map slurp (character-files))))

(defn available-chars []
  (into #{} (keys (char->pattern))))
