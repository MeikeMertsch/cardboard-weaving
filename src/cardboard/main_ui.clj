(ns cardboard.main-ui
  (:gen-class)
  (:require [seesaw.core :refer :all]
            [seesaw.chooser :as sc]
            [cardboard.preview-canvas :as pre]
            [cardboard.overview-canvas :as ov]
            [cardboard.input :as in]
            [cardboard.constants :refer :all]))

(native!)

;;; Items
(def input-for-string (text default-text))
(def save-button (button :text save-button-text))
(def overview-button (button :text overview-button-text))
(def error-label (label empty-string))
(def error-panel (horizontal-panel :items [error-label]
                                   :size [900 :by 20]))
(def form-for-saving (grid-panel :columns 2
                                 :items [input-for-string
                                         save-button]
                                 :size [900 :by 30]))
(def preview-canvas (pre/preview-canvas))
(def preview-panel (horizontal-panel :items [preview-canvas]))
(def preview-scroll (scrollable preview-panel))
(def main-panel (vertical-panel :items [form-for-saving
                                        error-panel
                                        preview-scroll
                                        overview-button]))

(def pgm-window
  (frame
    :title app-title
    :content main-panel
    :width 900
    :height 140))

;;; Actions
(defn show-validation-result [string]
  (config! error-label :text string))

(defn handle-string-changed [caller]
  (in/preview-new-string preview-canvas (value caller))
  (show-validation-result (in/validate (value caller))))

(defn handle-submit [action]
  (sc/choose-file :type :save
                  :success-fn (partial in/save-instructions (value input-for-string))
                  :selection-mode :files-only)
  (alert action saved-instructions-text))

(defn keypress [caller]
  (let [key (.getKeyChar caller)]
    (if (= key \newline)
      (handle-submit caller))))

;;; Listeners
(listen save-button :action handle-submit)
(listen overview-button :action (fn [_] (ov/preview)))
(listen input-for-string :key-pressed keypress)
(listen input-for-string :key handle-string-changed)

;;; Showing The UI
(show! pgm-window)
(handle-string-changed input-for-string)