(ns cardboard.main-ui
  (:gen-class)
  (:require [seesaw.core :refer :all]
            [seesaw.chooser :as sc]
            [cardboard.preview-canvas :as pre]
            [cardboard.input :as in]
            [cardboard.constants :refer :all]))

(native!)

;----- Items
(def input-for-string (text default-text))
(def save-button (button :text save-button-text))
(def error-label (label :minimum-size  [900 :by 20]))
(def form-for-saving (grid-panel :columns 2
                                 :items [input-for-string
                                         save-button]))
(def preview-panel (horizontal-panel :items [pre/preview-canvas]
                                     :size [900 :by 300]))
(def main-panel (vertical-panel :items [form-for-saving
                                        error-label
                                        preview-panel]))
(def pgm-window
  (frame
    :title app-title
    :content main-panel
    :width 900))

;----- Actions
(defn show-error [string]
  (config! error-label :text string))

(defn handle-submit [action]
  (sc/choose-file :type :save
                  :success-fn (partial in/save-instructions (value input-for-string))
                  :selection-mode :files-only)
  (alert action saved-instructions-text))

(defn handle-string-changed [caller]
  (in/preview-new-string (value caller)))

(defn keypress [caller]
  (let [key (.getKeyChar caller)]
    (if (= key \newline)
      (handle-submit caller))))

;----- Listeners
(listen save-button :action handle-submit)
(listen input-for-string :key-pressed keypress)
(listen input-for-string :key handle-string-changed)

;----- Showing The UI
(show! pgm-window)