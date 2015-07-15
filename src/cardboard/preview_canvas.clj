(ns cardboard.preview-canvas
  (:require [seesaw.core :refer :all]
            [seesaw.graphics :as sg]
            [seesaw.color :as scol]
            [cardboard.constants :refer :all]))

;----- Items
(def preview-canvas (canvas :paint nil))

(def style-foreground (sg/style :background (scol/color :black)))
(def style-background (sg/style :background nil))

(defn rectangle [column row]
  (let [rect-height 2
        rect-width 4]
    (sg/rect (* rect-width column) (* rect-height row) rect-width rect-height)))

;----- Preview
(defn pixel-state [pixel]
  (if (= pixel foreground-pixel)
    style-foreground
    style-background))

(defn row-rectangles [row row-number]
  (for [column-number (range (count row))
        :let [pixel (nth row column-number)]
        :let [rect (rectangle column-number row-number)]
        :let [style (pixel-state pixel)]]
    [rect style]))

(defn rectangles [pattern]
  (->> (for [row-number (range (count pattern))
             :let [row (nth pattern row-number)]]
         (row-rectangles row row-number))
       (apply concat)))

(defn paint [rects _ g]
  (doseq [[rect style] rects]
    (sg/draw g rect style)))

(defn preview [pattern-in-rows]
  (config! preview-canvas :paint #(paint (rectangles pattern-in-rows) %1 %2)))



