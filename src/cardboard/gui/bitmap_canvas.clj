(ns cardboard.gui.bitmap-canvas
  (:require [seesaw.core :refer :all]
            [seesaw.graphics :as sg]
            [seesaw.color :as scol]
            [cardboard.constants :refer :all]
            [cardboard.pattern :as pat]))

;;; Items
(def style-foreground (sg/style :background (scol/color :black)))
(def style-background (sg/style :background (scol/color :white)))

(defn bitmap-canvas []
  (canvas :paint nil))

(defn pixel [column row size]
  (let [width (size :width)
        height (size :height)]
  (sg/rect (* (inc width) column) (* (inc height) row) width height)))

;;; Preview
(defn pixel-style [filling]
  (if (= filling foreground-pixel)
    style-foreground
    style-background))

(defn row-pixels [row row-number size]
  (for [column-number (range (count row))
        :let [pxl (pixel column-number row-number size)
              filling (nth row column-number)
              style (pixel-style filling)]]
    [pxl style]))

(defn pixels [pattern size]
  (->> (for [row-number (range (count pattern))
             :let [row (nth pattern row-number)]]
         (row-pixels row row-number size))
       (apply concat)))

(defn paint [pxls _ graphic]
  (doseq [[pxl style] pxls]
    (sg/draw graphic pxl style)))

(defn build-canvas [canvas pattern content pxl-size]
  (config! canvas :paint #(paint (pixels pattern pxl-size) %1 %2)
           :user-data {:content content
                       :pattern pattern}))

(defn render [canvas pxl-size content]
  (build-canvas canvas (pat/string->pattern (str content)) content pxl-size))