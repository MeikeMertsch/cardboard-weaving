(ns cardboard.character-editing
  (:require [seesaw.core :refer :all]
            [seesaw.mouse :as mou]
            [cardboard.preview-canvas :as pre]
            [cardboard.pattern :as pat]
            [cardboard.pixel-change :as pc]
            [cardboard.constants :refer :all]))

;;; GUI Elements
(def save-button (button :text save-letter-button-text))
(def cancel-button (button :text cancel-button-text))
(def button-panel (horizontal-panel :items [save-button
                                            cancel-button]))
(def main-panel (vertical-panel))

(def editing-window
  (frame
    :title overview-title
    :width (* 10 (zoom-size :width))
    :height (+ status-bar-height (* 17 (zoom-size :height)))
    :content main-panel))

(listen cancel-button :action dispose!)

;;; Logic
(defn new-pattern [canvas]
  (pc/invert-pixel (mou/location canvas) ((user-data canvas) :pattern)))

(defn paint [pattern unknown graphic]
  (-> pattern
      (pre/pixels  zoom-size)
      (pre/paint  unknown graphic)))

(defn handle-click [canvas]
  (let [pattern (new-pattern canvas)]
    (config! canvas :paint (partial paint pattern)
                    :user-data {:pattern pattern})))

(defn paint-canvas [character character-canvas]
  (pre/preview character-canvas zoom-size (pat/string->pattern character))
  (listen character-canvas :mouse-clicked handle-click)
  character-canvas)

(defn open [character]
  (config! main-panel :items [(paint-canvas character (pre/preview-canvas))
                              button-panel])
  (show! editing-window))