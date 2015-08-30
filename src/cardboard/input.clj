(ns cardboard.input
  (:require [cardboard.core :as cc]
            [cardboard.validation :as val]
            [cardboard.preview-canvas :as pre]
            [cardboard.constants :refer :all]))

;;; Preview Related
(defn preview-new-string [canvas new-string]
  (->> new-string
       (pre/preview canvas preview-size)))

;;; Saving Related
(defn guarantee-txt [file]
  (if (not (.endsWith (str file) default-extension))
    (str file default-extension)
    file))

(defn save-instructions [string file]
  (if (not (nil? file))
    (->> file
         guarantee-txt
         (cc/save-instructions-for string))))

;;; Validation Related
(defn validation-message-for [invalid-chars]
  (->> (interpose ", " invalid-chars)
       (apply str)
       (str invalid-characters)))

(defn validate [string]
  (let [validation-result (val/validate string)]
    (if (= :ok (:outcome validation-result))
      empty-string
      (validation-message-for (:error validation-result)))))


