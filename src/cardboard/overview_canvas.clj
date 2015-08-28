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

(defn open-letter [letter _]
  (alert letter))

(defn paint-canvas [letter letter-canvas]
  (pre/preview letter-canvas overview-size (pat/string->pattern letter))
  (listen letter-canvas :mouse-clicked (partial open-letter letter))
  letter-canvas)

(defn canvases [letters]
  (for [letter letters
        :let [letter-canvas (pre/preview-canvas)]]
    (paint-canvas letter letter-canvas)))

(defn overview-panel []
  (scrollable (grid-panel :columns 8
                          :vgap 10
                          :hgap 10
                          :size [1200 :by (* 180 7)]
                          :items (canvases (sort dc/available-chars)))))

(defn preview []
  (config! overview-window :content (overview-panel))
  (show! overview-window))