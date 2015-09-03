(ns cardboard.character-editing
  (:require [seesaw.core :refer :all]
            [seesaw.mouse :as mou]
            [cardboard.bitmap-canvas :as bc]
            [cardboard.pixel-change :as pc]
            [cardboard.size :as ps]
            [cardboard.saving :as s]
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
    :content main-panel))


;;; Logic
(defn canvas-information []
  (user-data (select main-panel [:#character-canvas])))

(defn updated-pattern [canvas]
  (pc/invert-pixel (mou/location canvas) ((canvas-information) :pattern)))

(defn paint [pattern widget graphic]
  (-> pattern
      (bc/pixels zoom-size)
      (bc/paint widget graphic)))

(defn fill-canvas [canvas pattern]
  (config! canvas :paint (partial paint pattern)
                  :user-data (assoc (canvas-information) :pattern pattern)))

(defn handle-click [canvas]
  (->> (updated-pattern canvas)
       (fill-canvas canvas)))

(defn character-canvas [character new-canvas]
  (bc/render new-canvas zoom-size character)
  (listen new-canvas :mouse-clicked handle-click)
  (config! new-canvas :id :character-canvas)
  new-canvas)

(defn open [character]
  (config! main-panel :items [(character-canvas character (bc/bitmap-canvas))
                              button-panel])
  (config! (select main-panel [:#character-canvas]) :size (ps/screen-size zoom-size character))
  (pack! editing-window)
  (show! editing-window))


;;; Non-Dynamic Listeners
(defn save-character [caller]
  (s/save-character (:string (canvas-information))
                     (:pattern (canvas-information)))
  (dispose! caller))

(listen cancel-button :action dispose!)
(listen save-button :action save-character)
