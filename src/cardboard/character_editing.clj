(ns cardboard.character-editing
  (:require [seesaw.core :refer :all]
            [seesaw.mouse :as mou]
            [cardboard.preview-canvas :as pre]
            [cardboard.pattern :as pat]
            [cardboard.pixel-change :as pc]
            [cardboard.constants :refer :all]))

(def save-button (button :text "Save"))
(def cancel-button (button :text "Cancel"))
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

(defn new-pattern [caller]
  (pc/invert-pixel (mou/location caller) (user-data caller)))

(defn paint [pattern unknown graphic]
  (-> pattern
      (pre/pixels zoom-size)
      (pre/paint unknown graphic)))

(defn handle-click [caller]
  (let [pattern (new-pattern caller)]
    (config! caller :paint (partial paint pattern)
                    :user-data pattern)))

(defn paint-canvas [character character-canvas]
  (pre/preview character-canvas zoom-size (pat/string->pattern character))
  (listen character-canvas :mouse-clicked handle-click)
  character-canvas)

(defn open [character]
  (config! main-panel :items [(paint-canvas character (pre/preview-canvas))
                                button-panel])
  (show! editing-window))