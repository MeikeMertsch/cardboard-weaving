(ns cardboard.expectations-options
  (require [seesaw.core :refer :all]
           [cardboard.font :as f]
           [cardboard.gui.main-ui :as mu]
           [cardboard.constants :refer :all]))

(defn start-with-test-data {:expectations-options :before-run} []
  (f/update-mapping! default-font))

(defn end-with-proper-data {:expectations-options :after-run} []
  (f/update-mapping! (value mu/font-choice)))