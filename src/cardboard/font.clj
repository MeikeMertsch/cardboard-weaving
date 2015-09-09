(ns cardboard.font
  (require [cardboard.constants :refer :all]))

;;; Read From Files
(defn- reader [dir]
  (clojure.java.io/file dir))

(defn- keep-only-character-files [file-list]
  (filter #(.endsWith % character-extension) file-list))

(defn- character-files [font]
  (->> (file-seq (reader (str font-location font)))
       (map str)
       keep-only-character-files))

(defn- remove-substring [subs string]
  (clojure.string/replace string subs empty-string))

(defn- int-str->character [string]
  (->> string
       bigint
       char))

(defn- filename->character [string font]
  (->> string
       (remove-substring character-extension)
       (remove-substring (str font-location font path-separator))
       int-str->character))

(defn- create-mapping [font]
  (let [files (character-files font)]
    (zipmap (map #(filename->character % font) files)
            (map slurp files))))

(def mapping-char->pattern
  (atom (create-mapping default-font)))

(defn- keep-only-directories [files]
  (filter #(.isDirectory %) files))

;;; Functions That Are Needed From Outside

(defn fonts []
  (->> (file-seq (reader font-location))
       keep-only-directories
       (map str)
       (map (partial remove-substring font-location))
       (remove (partial = (remove-substring path-separator font-location)))))

(defn char->pattern []
  (deref mapping-char->pattern))

(defn available-chars []
  (into #{} (keys (char->pattern))))

(defn update-mapping! [font]
  (reset! mapping-char->pattern (create-mapping font)))