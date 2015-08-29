(ns cardboard.character-editing
  (:require [seesaw.core :refer :all]
            [seesaw.mouse :as mou]
            [cardboard.preview-canvas :as pre]
            [cardboard.pattern :as pat]
            [cardboard.pixel-change :as pc]
            [cardboard.constants :refer :all]))

(def editing-window
  (frame
    :title overview-title
    :width (* 10 (zoom-size :width))
    :height (+ status-bar-height (* 17 (zoom-size :height)))))

(defn new-pattern [caller]
  (pc/invert-pixel (mou/location caller) (user-data caller)))

(defn paint [caller unknown graphic]
  (-> (new-pattern caller)
      (pre/pixels zoom-size)
      (pre/paint unknown graphic)))

(defn handle-click [caller]
  (config! caller :paint (partial paint caller)
                  :user-data (new-pattern caller)))

(defn paint-canvas [character character-canvas]
  (pre/preview character-canvas zoom-size (pat/string->pattern character))
  (listen character-canvas :mouse-clicked handle-click)
  character-canvas)

(defn open [character]
  (config! editing-window :content (paint-canvas character (pre/preview-canvas)))
  (show! editing-window))