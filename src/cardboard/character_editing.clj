(ns cardboard.character-editing
  (:require [seesaw.core :refer :all]
            [seesaw.mouse :as mou]
            [cardboard.preview-canvas :as pre]
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
    :height (+ status-bar-height button-bar-height (* 17 (zoom-size :height)))
    :content main-panel))

(defn something [_]
  (let [userdata (user-data (select main-panel [:#character-canvas]))]
    (alert (first (map int (:string userdata))))
    (spit (str "resources/default/" (first (map int (:string userdata))) ".txt") (:pattern userdata))))

(listen cancel-button :action dispose!)
(listen save-button :action something)

;;; Logic
(defn updated-pattern [canvas]
  (pc/invert-pixel (mou/location canvas) ((user-data canvas) :pattern)))

(defn paint [pattern unknown graphic]
  (-> pattern
      (pre/pixels  zoom-size)
      (pre/paint  unknown graphic)))

(defn fill-canvas [canvas pattern]
  (config! canvas :paint (partial paint pattern)
                  :user-data (assoc (user-data canvas) :pattern pattern)))

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

