(ns cardboard.overview-canvas
  (:require [seesaw.core :refer :all]
            [seesaw.border :as sb]
            [cardboard.preview-canvas :as pre]
            [cardboard.pattern :as pat]
            [cardboard.default_chars :as dc]
            [cardboard.constants :refer :all]))

(defn letter-panel [letter-pattern]
  (let [letter-canvas (pre/preview-canvas)
        letter-panel (horizontal-panel :items [letter-canvas] :border (sb/line-border))]
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

(defn preview []
  (let [my-xyz-panel (scrollable (grid-panel :columns 8
                                             :size [1400 :by 2000]
                                             :items (panels (map pat/string->pattern (sort dc/available-chars)))))]
    (config! overview-window :content my-xyz-panel)
    (show! overview-window)))
