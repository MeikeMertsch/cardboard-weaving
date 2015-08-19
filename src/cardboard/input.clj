(ns cardboard.input
(:require [cardboard.core :as cc]
          [cardboard.preview-canvas :as pre]
          [cardboard.constants :refer :all]))

(defn preview-new-string [new-string]
  (->> new-string
       cc/pattern-in-rows
       pre/preview))

(defn save-instructions [string _ file]
  (if (not (nil? file))
    (cc/save-instructions-for string file)))

(defn guarantee-txt [_ file]
  (if (not (.endsWith (str file) default-extension))
    (str file default-extension)
    file))

