(ns cardboard.overview-canvas
  (:require [seesaw.core :refer :all]
            [cardboard.preview-canvas :as pre]
            [cardboard.pattern :as pat]
            [cardboard.default_chars :as dc]
            [cardboard.constants :refer :all]))

(defn letter-panel [letter-pattern]
  (let [letter-canvas (pre/preview-canvas)
        letter-panel (horizontal-panel :items [letter-canvas])]
    (pre/preview letter-canvas overview-size letter-pattern)
    letter-panel))

(defn panels [letter-patterns]
  (for [pattern letter-patterns]
    (letter-panel pattern)))

(def overview-window
  (frame
    :title overview-title
    :width 1400
    :height 800))

(defn main-panel []
  (scrollable (grid-panel :columns 8
                          :vgap 10
                          :hgap 10
                          :size [1200 :by (* 170 7)]
                          :items (panels (map pat/string->pattern (sort dc/available-chars))))))

(defn preview []
  (config! overview-window :content (main-panel))
  (show! overview-window))
