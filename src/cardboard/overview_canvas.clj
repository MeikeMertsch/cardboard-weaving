(ns cardboard.overview-canvas
  (:require [seesaw.core :refer :all]
            [cardboard.preview-canvas :as pre]
            [cardboard.pattern :as pat]
            [cardboard.default_chars :as dc]
            [cardboard.constants :refer :all]))

(defn paint-canvas [letter letter-canvas]
  (pre/preview letter-canvas overview-size (pat/string->pattern letter))
  letter-canvas)

(defn panels [letters]
  (for [letter letters
        :let [cnvs (pre/preview-canvas)]]
    (paint-canvas letter cnvs)))

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