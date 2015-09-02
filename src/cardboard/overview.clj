(ns cardboard.overview
  (:require [seesaw.core :refer :all]
            [cardboard.bitmap-canvas :as pre]
            [cardboard.character-editing :as che]
            [cardboard.chars :as c]
            [cardboard.constants :refer :all]))

(def overview-window
  (frame
    :title overview-title
    :width 1400
    :height 800))

(defn open-character [character _]
  (che/open character))

(defn paint-canvas [character character-canvas]
  (pre/render character-canvas overview-size character)
  (listen character-canvas :mouse-clicked (partial open-character character))
  character-canvas)

(defn character-canvases [characters]
  (for [letter characters
        :let [letter-canvas (pre/bitmap-canvas)]]
    (paint-canvas letter letter-canvas)))

(defn overview-panel []
  (scrollable (grid-panel :columns 8
                          :vgap 10
                          :hgap 10
                          :size [(* 9 10 8) :by (* 6 18 7)]
                          :items (character-canvases (sort (c/available-chars))))))

(defn reload [_]
  (config! overview-window :content (overview-panel)))

(defn preview []
  (reload overview-window)
  (show! overview-window))

(listen overview-window :focus-gained reload)