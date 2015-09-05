(ns cardboard.font
  (require [cardboard.constants :refer :all]))

;;; Read Available Characters From Files
(defn filereader [font]
  (clojure.java.io/file (str font-location font)))

(defn keep-only-character-files [file-list]
  (filter #(.endsWith % character-extension) file-list))

(defn character-files [font]
  (->> (file-seq (filereader font))
       (map str)
       keep-only-character-files))

(defn remove-substring [subs string]
  (clojure.string/replace string subs ""))

(defn int-str->character [string]
  (->> string
       bigint
       char))

(defn filename->character [string font]
  (->> string
       (remove-substring character-extension)
       (remove-substring (str font-location font "/"))
       int-str->character))

(defn create-mapping [font]
  (let [files (character-files font)]
    (zipmap (map #(filename->character % font) files)
            (map slurp files))))

(def mapping-char->pattern
  (atom (create-mapping "default")))

;;; Functions That Are Needed From Outside
(defn char->pattern []
  (deref mapping-char->pattern))

(defn available-chars []
  (into #{} (keys (char->pattern))))

(defn update-mapping! [font]
  (reset! mapping-char->pattern (create-mapping font)))