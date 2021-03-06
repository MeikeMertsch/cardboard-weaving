(ns cardboard.gui.main-ui
  (:gen-class)
  (:require [seesaw.core :refer :all]
            [seesaw.chooser :as sc]
            [cardboard.gui.bitmap-canvas :as bc]
            [cardboard.gui.overview :as ov]
            [cardboard.gui.input :as in]
            [cardboard.reset :as res]
            [cardboard.constants :refer :all]
            [cardboard.font :as f]))

(native!)

;;; Items
(def input-for-string (text default-text))
(def save-button (button :text save-button-text))
(def font-choice (combobox :model (f/fonts)))
(def font-button (button :text overview-button-text))
(def font-panel (border-panel :center font-choice
                              :east font-button
                              :size [900 :by 30]))
(def error-label (label empty-string))
(def error-panel (horizontal-panel :items [error-label]
                                   :size [900 :by 20]))
(def form-for-saving (grid-panel :columns 2
                                 :items [input-for-string
                                         save-button]
                                 :size [900 :by 30]))
(def preview-canvas (bc/bitmap-canvas))
(def preview-panel (horizontal-panel :items [preview-canvas]))
(def preview-scroll (scrollable preview-panel))
(def main-panel (vertical-panel :items [font-panel
                                        form-for-saving
                                        error-panel
                                        preview-scroll]))

(def pgm-window
  (frame
    :title app-title
    :content main-panel
    :width 900
    :height 160))

;;; Actions
(defn- show-validation-result [string]
  (config! error-label :text string))

(defn- handle-string-changed [caller]
  (bc/render-from-content preview-canvas preview-size (value caller))
  (show-validation-result (in/validate (value caller))))

(defn- save [_ file]
  (in/save-instructions (value input-for-string) file)
  (alert action saved-instructions-text))

(defn- handle-submit [_]
  (sc/choose-file :type :save
                  :success-fn save
                  :selection-mode :files-only))

(defn- keypress [caller]
  (let [key (.getKeyChar caller)]
    (if (= key \newline)
      (handle-submit caller)
      (handle-string-changed caller))))

(defn- handle-font-changed [caller]
  (f/update-mapping! (selection caller))
  (handle-string-changed input-for-string))

;;; Listeners
(listen save-button :action handle-submit)
(listen font-button :action (fn [_] (ov/render (selection font-choice))))
(listen input-for-string :key keypress)
(listen font-choice :selection handle-font-changed)


;;; Showing The UI
(res/prefill-default-characters)
(show! pgm-window)
(handle-font-changed font-choice)