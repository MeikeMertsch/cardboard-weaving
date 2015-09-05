(ns cardboard.expectations-options
  (require [cardboard.font :as f]))

(defn start-with-test-data {:expectations-options :before-run} []
  (f/update-mapping! "default"))