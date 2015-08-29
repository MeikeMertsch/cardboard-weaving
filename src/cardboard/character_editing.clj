(ns cardboard.character-editing
  (:require [seesaw.core :refer :all]
            [seesaw.graphics :as sg]
            [seesaw.color :as scol]
            [seesaw.mouse :as mou]
            [cardboard.preview-canvas :as pre]
            [cardboard.pattern :as pat]
            [cardboard.constants :refer :all]))

(def editing-window
  (frame
    :title overview-title
    :width (* 10 (zoom-size :width))
    :height (+ status-bar-height (* 17 (zoom-size :height)))))

(defn new-pxl [loc]
  [[(sg/rect (first loc) (last loc) 15 10) (sg/style :background (scol/color :red))]])

(defn do-something [character-canvas caller]
  (config! character-canvas :paint #(pre/paint (new-pxl (mou/location caller))
                                               %1 %2)))

(defn paint-canvas [character character-canvas]
  (pre/preview character-canvas zoom-size (pat/string->pattern character))
  (listen character-canvas :mouse-clicked (partial do-something character-canvas))
  character-canvas)

(defn open [character]
  (config! editing-window :content (paint-canvas character (pre/preview-canvas)))
  (show! editing-window))