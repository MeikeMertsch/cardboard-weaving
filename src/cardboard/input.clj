(ns cardboard.input
  (:require [cardboard.saving :as s]
            [cardboard.validation :as val]
            [cardboard.bitmap-canvas :as bc]
            [cardboard.constants :refer :all]))

;;; Preview Related
(defn preview-new-string [canvas new-string]
  (->> new-string
       (bc/render canvas preview-size)))

;;; Saving Related
(defn guarantee-txt [file]
  (if (not (.endsWith (str file) instruction-extension))
    (str file instruction-extension)
    file))

(defn save-instructions [string file]
  (->> file
       guarantee-txt
       (s/save-instructions-for string)))

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


