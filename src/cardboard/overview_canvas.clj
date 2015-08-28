(ns cardboard.overview-canvas
  (:require [seesaw.core :refer :all]
            [seesaw.border :as sb]
            [cardboard.preview-canvas :as pre]
            [cardboard.pattern :as pat]
            [cardboard.constants :refer :all]))


(def overview-canvas (pre/preview-canvas))
(def overview-canvas-a (pre/preview-canvas))
(def overview-canvas-b (pre/preview-canvas))
(def overview-canvas-c (pre/preview-canvas))
(def overview-canvas-d (pre/preview-canvas))
(def overview-canvas-e (pre/preview-canvas))
(def overview-canvas-f (pre/preview-canvas))

(def overview-panel (horizontal-panel :items [overview-canvas]))


(def a-panel (horizontal-panel :items [overview-canvas-a] :border (sb/line-border)))
(def b-panel (horizontal-panel :items [overview-canvas-b]))
(def c-panel (horizontal-panel :items [overview-canvas-c]))
(def d-panel (horizontal-panel :items [overview-canvas-d]))
(def e-panel (horizontal-panel :items [overview-canvas-e]))
(def f-panel (horizontal-panel :items [overview-canvas-f]))

(def my-xyz-panel (grid-panel :columns 8
                              :items [overview-panel
                                      a-panel
                                      b-panel
                                      c-panel
                                      d-panel
                                      e-panel
                                      f-panel]))

(defn preview []
  (pre/preview overview-canvas overview-size (pat/string->pattern "a"))
  (pre/preview overview-canvas-a overview-size (pat/string->pattern "A"))
  (pre/preview overview-canvas-b overview-size (pat/string->pattern "B"))
  (pre/preview overview-canvas-c overview-size (pat/string->pattern "C"))
  (pre/preview overview-canvas-d overview-size (pat/string->pattern "D"))
  (pre/preview overview-canvas-e overview-size (pat/string->pattern "E"))
  (pre/preview overview-canvas-f overview-size (pat/string->pattern "F")))