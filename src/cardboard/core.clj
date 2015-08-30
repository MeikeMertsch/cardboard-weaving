(ns cardboard.core
  (:require [cardboard.pattern :as p])
  (:require [cardboard.print_layout :as l])
  (:require [cardboard.saving :as s])
  (:require [cardboard.validation :as v])
  (:require [cardboard.constants :refer :all]))

(defn save-instructions-for [string file-path]
  (->> (p/string->pattern string)
       (l/string-n-pattern->layout (:valid (v/validate string)))
       (s/save file-path)))

(defn character-file-name [character]
  (int (first character)))

(defn save-character [character pattern]
  (s/save (str default-character-location
               (character-file-name character)
               character-extension)
          (p/pattern->string pattern)))

(defn pattern-in-rows [string]
  (if (= empty-string string)
    empty-pattern
    (p/string->pattern string)))

