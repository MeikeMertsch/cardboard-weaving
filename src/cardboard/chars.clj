(ns cardboard.chars
  (require [cardboard.constants :refer :all]
           [cardboard.default_chars :as dc]))

;;; Read Available Characters From Files
(def filereader (clojure.java.io/file default-character-location))

(defn keep-only-character-files [file-list]
  (filter #(.endsWith % character-extension) file-list))

(defn character-files []
  (->> (file-seq filereader)
       (map str)
       keep-only-character-files))

(defn remove-substring [subs string]
  (clojure.string/replace string subs ""))

(defn int-str->character [string]
  (->> string
       bigint
       char))

(defn filename->character [string]
  (->> string
       (remove-substring character-extension)
       (remove-substring default-character-location)
       int-str->character))

(defn create-mapping []
  (zipmap (map filename->character (character-files))
          (map slurp (character-files))))

(def mapping-char->pattern
  (atom (create-mapping)))

;;; Functions That Are Needed From Outside
(defn char->pattern []
  (deref mapping-char->pattern))

(defn available-chars []
  (into #{} (keys (char->pattern))))

(defn letter-space []
  dc/letter-space)

(defn update-mapping! []
  (reset! mapping-char->pattern (create-mapping)))