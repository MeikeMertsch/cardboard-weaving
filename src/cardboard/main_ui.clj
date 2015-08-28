(ns cardboard.main-ui
  (:gen-class)
  (:require [seesaw.core :refer :all]
            [seesaw.chooser :as sc]
            [seesaw.border :as sb]
            [cardboard.preview-canvas :as pre]
            [cardboard.input :as in]
            [cardboard.pattern :as pat]
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
(def overview-canvas (pre/preview-canvas))
(def overview-canvas-a (pre/preview-canvas))
(def overview-canvas-b (pre/preview-canvas))
(def overview-canvas-c (pre/preview-canvas))
(def overview-canvas-d (pre/preview-canvas))
(def overview-canvas-e (pre/preview-canvas))
(def overview-canvas-f (pre/preview-canvas))

(def preview-panel (horizontal-panel :items [preview-canvas]))
(def overview-panel (horizontal-panel :items [overview-canvas]))


(def a-panel (horizontal-panel :items [overview-canvas-a] :border (sb/line-border)))
(def b-panel (horizontal-panel :items [overview-canvas-b]))
(def c-panel (horizontal-panel :items [overview-canvas-c]))
(def d-panel (horizontal-panel :items [overview-canvas-d]))
(def e-panel (horizontal-panel :items [overview-canvas-e]))
(def f-panel (horizontal-panel :items [overview-canvas-f]))

(def my-xyz-panel (grid-panel :columns 8
                              :items [overview-panel
                                      a-panel
                                      b-panel
                                      c-panel
                                      d-panel
                                      e-panel
                                      f-panel]))

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

(def overview-window
  (frame
    :title overview-title
    :content my-xyz-panel
    :width 1400
    :height 190))


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

(defn open-overview [_]
  (show! overview-window)
  (pre/preview overview-canvas overview-size (pat/string->pattern "a"))
  (pre/preview overview-canvas-a overview-size (pat/string->pattern "A"))
  (pre/preview overview-canvas-b overview-size (pat/string->pattern "B"))
  (pre/preview overview-canvas-c overview-size (pat/string->pattern "C"))
  (pre/preview overview-canvas-d overview-size (pat/string->pattern "D"))
  (pre/preview overview-canvas-e overview-size (pat/string->pattern "E"))
  (pre/preview overview-canvas-f overview-size (pat/string->pattern "F")))

;;; Listeners
(listen save-button :action handle-submit)
(listen overview-button :action open-overview)
(listen input-for-string :key-pressed keypress)
(listen input-for-string :key handle-string-changed)

;;; Showing The UI
(show! pgm-window)
(handle-string-changed input-for-string)