(ns cardboard.overview-canvas
  (:require [seesaw.core :refer :all]
            [cardboard.preview-canvas :as pre]
            [cardboard.pattern :as pat]
            [cardboard.default_chars :as dc]
            [cardboard.constants :refer :all]))

(def overview-window
  (frame
    :title overview-title
    :width 1400
    :height 800))

(defn open-character [character _]
  (alert character))

(defn paint-canvas [character character-canvas]
  (pre/preview character-canvas overview-size (pat/string->pattern character))
  (listen character-canvas :mouse-clicked (partial open-character character))
  character-canvas)

(defn character-canvases [characters]
  (for [letter characters
        :let [letter-canvas (pre/preview-canvas)]]
    (paint-canvas letter letter-canvas)))

(defn overview-panel []
  (scrollable (grid-panel :columns 8
                          :vgap 10
                          :hgap 10
                          :size [(* 9 10 8) :by (* 6 18 7)]
                          :items (character-canvases (sort dc/available-chars)))))

(defn preview []
  (config! overview-window :content (overview-panel))
  (show! overview-window))