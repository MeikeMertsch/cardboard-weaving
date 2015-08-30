(ns cardboard.saving)

(defn save [file instructions]
  (spit file instructions))

(defn save-character [file pattern]
  (save (str "resources/default/" file ".txt")
        pattern))