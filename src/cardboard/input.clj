(ns cardboard.input
  (:require [cardboard.core :as cc]
            [cardboard.preview-canvas :as pre]
            [cardboard.constants :refer :all]))

(defn preview-new-string [new-string]
  (->> new-string
       cc/pattern-in-rows
       pre/preview))

(defn guarantee-txt [file]
  (if (not (.endsWith (str file) default-extension))
    (str file default-extension)
    file))

(defn save-instructions [string _ file]
  (if (not (nil? file))
    (->> file
         guarantee-txt
         (cc/save-instructions-for string))))

(defn error-message-for [invalid-chars]
  (->> (interpose ", " invalid-chars)
       (apply str)
       (str invalid-characters)))

(defn validate [string]
  (cc/validate string))


