(ns cardboard.gui.input
  (:require [cardboard.saving :as s]
            [cardboard.validation :as val]
            [cardboard.constants :refer :all]))

;;; Saving Related
(defn- guarantee-txt [file]
  (if (not (.endsWith (str file) instruction-extension))
    (str file instruction-extension)
    file))

(defn save-instructions [string file]
  (->> file
       guarantee-txt
       (s/save-instructions-for string)))

;;; Validation Related
(defn- validation-message-for [invalid-chars]
  (->> (interpose ", " invalid-chars)
       (apply str)
       (str invalid-characters-message)))

(defn validate [string]
  (let [validation-result (val/validate string)]
    (if (= :ok (:outcome validation-result))
      empty-string
      (validation-message-for (:error validation-result)))))


