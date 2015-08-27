(ns cardboard.preview-canvas
  (:require [seesaw.core :refer :all]
            [seesaw.graphics :as sg]
            [seesaw.color :as scol]
            [cardboard.constants :refer :all]))

;;; Items
(def preview-canvas (canvas :paint nil))
(def style-foreground (sg/style :background (scol/color :black)))
(def style-background (sg/style :background nil))
(def pixel-height 2)
(def pixel-width 3)

(defn pixel [column row]
  (sg/rect (* pixel-width column) (* pixel-height row) pixel-width pixel-height))

;;; Preview
(defn pixel-style [filling]
  (if (= filling foreground-pixel)
    style-foreground
    style-background))

(defn row-pixels [row row-number]
  (for [column-number (range (count row))
        :let [pxl (pixel column-number row-number)
              filling (nth row column-number)
              style (pixel-style filling)]]
    [pxl style]))

(defn pixels [pattern]
  (->> (for [row-number (range (count pattern))
             :let [row (nth pattern row-number)]]
         (row-pixels row row-number))
       (apply concat)))

(defn paint [pxls _ graphic]
  (doseq [[pxl style] pxls]
    (sg/draw graphic pxl style)))

(defn preview [pattern-in-rows]
  (config! preview-canvas :paint #(paint (pixels pattern-in-rows) %1 %2)))



