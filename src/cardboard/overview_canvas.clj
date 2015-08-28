(ns cardboard.overview-canvas
  (:require [seesaw.core :refer :all]
            [cardboard.preview-canvas :as pre]
            [cardboard.pattern :as pat]
            [cardboard.default_chars :as dc]
            [cardboard.constants :refer :all]))

(defn letter-panel [letter]
  (let [letter-canvas (pre/preview-canvas)
        letter-panel (horizontal-panel :items [letter-canvas])]
    (pre/preview letter-canvas overview-size (pat/string->pattern letter))
    letter-panel))

(defn panels [letters]
  (for [letter letters]
    (letter-panel letter)))

(def overview-window
  (frame
    :title overview-title
    :width 1400
    :height 800))

(defn main-panel []
  (scrollable (grid-panel :columns 8
                          :vgap 10
                          :hgap 10
                          :size [1200 :by (* 180 7)]
                          :items (panels (sort dc/available-chars)))))

(defn preview []
  (config! overview-window :content (main-panel))
  (show! overview-window))