(ns cardboard.character-editing
  (:require [seesaw.core :refer :all]
            [seesaw.mouse :as mou]
            [cardboard.preview-canvas :as pre]
            [cardboard.pixel-change :as pc]
            [cardboard.core :as cc]
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
    :height (+ status-bar-height button-bar-height (* 17 (zoom-size :height)))
    :content main-panel))


;;; Logic
(defn canvas-information []
  (user-data (select main-panel [:#character-canvas])))

(defn updated-pattern [canvas]
  (pc/invert-pixel (mou/location canvas) ((canvas-information) :pattern)))

(defn paint [pattern widget graphic]
  (-> pattern
      (pre/pixels  zoom-size)
      (pre/paint  widget graphic)))

(defn fill-canvas [canvas pattern]
  (config! canvas :paint (partial paint pattern)
                  :user-data (assoc (canvas-information) :pattern pattern)))

(defn handle-click [canvas]
  (->> (updated-pattern canvas)
       (fill-canvas canvas)))

(defn character-canvas [character character-canvas]
  (pre/preview character-canvas zoom-size character)
  (listen character-canvas :mouse-clicked handle-click)
  (config! character-canvas :id :character-canvas)
  character-canvas)

(defn open [character]
  (config! main-panel :items [(character-canvas character (pre/preview-canvas))
                              button-panel])
  (show! editing-window))


;;; Non-Dynamic Listeners
(defn save-character [caller]
  (cc/save-character (:string (canvas-information))
                     (:pattern (canvas-information)))
  (dispose! caller))

(listen cancel-button :action dispose!)
(listen save-button :action save-character)
