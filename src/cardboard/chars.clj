(ns cardboard.chars
  (require [cardboard.constants :refer :all]
           [cardboard.default_chars :as dc]))

(def filereader (clojure.java.io/file default-character-location))

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

(defn create-mapping []
  (zipmap (map filename->character (character-files))
          (map slurp (character-files))))

(def mapping-char->pattern
  (atom (create-mapping)))

(defn char->pattern []
  (deref mapping-char->pattern))

(defn available-chars []
  (into #{} (keys (char->pattern))))

(defn update-mapping []
  (reset! mapping-char->pattern (create-mapping)))