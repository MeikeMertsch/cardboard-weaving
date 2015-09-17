(ns cardboard.gui.character-editing
  (:require [seesaw.core :refer :all]
            [seesaw.mouse :as mou]
            [cardboard.gui.bitmap-canvas :as bc]
            [cardboard.pixel-change :as pc]
            [cardboard.size :as ps]
            [cardboard.saving :as s]
            [cardboard.constants :refer :all]))

;;; GUI Elements
(def save-button (button :text save-character-button-text))
(def cancel-button (button :text cancel-button-text))

(def button-panel (horizontal-panel :items [save-button
                                            cancel-button]))
(def main-panel (vertical-panel))

(def editing-window
  (frame
    :title character-editing-title
    :content main-panel))


;;; Logic
(defn- character-canvas []
  (select main-panel [:#character-canvas]))

(defn- canvas-information []
  (user-data (character-canvas)))

(defn- updated-pattern [canvas]
  (pc/invert-pixel (mou/location canvas) ((canvas-information) :pattern)))

(defn- redraw-canvas [canvas font pattern]
  (->> (bc/render canvas pattern (:content (canvas-information)) zoom-size)
       (#(config! % :user-data (assoc (canvas-information) :font font)))))

(defn- handle-click [canvas]
  (->> (updated-pattern canvas)
       (redraw-canvas canvas (:font (canvas-information)))))

(defn- render-canvas [font character canvas]
  (bc/render-from-content canvas zoom-size character)
  (listen canvas :mouse-clicked handle-click)
  (config! canvas :id :character-canvas
                      :user-data (assoc (user-data canvas) :font font))
  canvas)

(defn open [font character]
  (config! main-panel :items [(render-canvas font character (bc/bitmap-canvas))
                              button-panel])
  (config! (character-canvas) :size (ps/screen-size zoom-size character))
  (pack! editing-window)
  (show! editing-window))


;;; Non-Dynamic Listeners
(defn- save-character [caller]
  (s/save-character (:content (canvas-information))
                    (:pattern (canvas-information))
                    (:font (canvas-information)))
  (dispose! caller))

(listen cancel-button :action dispose!)
(listen save-button :action save-character)