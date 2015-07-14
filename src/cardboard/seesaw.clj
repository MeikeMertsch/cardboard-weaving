(ns cardboard.seesaw
  (:gen-class)
  (:require [seesaw.core :refer :all]
            [seesaw.chooser :refer :all]
            [seesaw.graphics :as sg]
            [seesaw.color :as scolor]
            [cardboard.core :refer :all]))

(native!)
; TODO: Take care of strings properly

;----- Items
(def input-for-string (text "Text to be turned into a pattern"))
(def send-button (button :text "Generate pattern"))


(def filled (sg/style :background (scolor/color :black)))
(def unfilled (sg/style :background (scolor/color :white)))
(def my-rect (sg/rect 10 10 6 4))

(defn my-draw [c g]
  (sg/draw g my-rect filled))

(def a-canvas (canvas :paint nil))



(def form-for-saving (grid-panel :columns 2
                                 :items [input-for-string
                                         send-button
                                         a-canvas]))

(def pgm-window
  (frame
    :title "Cardboard Weaving Patterns"
    :content form-for-saving
    :width 600))

;----- Actions
(defn send-string->core [action]
  (->> (choose-file :type :save)
       (save-instructions-for (value input-for-string)))
  (alert action "Thanks!\nYou saved your pattern"))

(defn row-rectangles [row row-number]
  (for [column-number (range (count row))
        :let [column (nth row column-number)]]
    (sg/rect (* 6 column-number) (* 4 row-number) 6 4)))

(defn rectangles [pattern]
  (->> (for [row-number (range (count pattern))
             :let [row (nth pattern row-number)]]
         (row-rectangles row row-number))
       (apply concat)))

(defn paint [rects c g]
  (doseq [rect rects]
    (sg/draw g rect filled)))

(defn preview [pattern-in-rows]
  (config! a-canvas :paint #(paint (rectangles pattern-in-rows) %1 %2)))

(defn keypress [e]
  (let [key (.getKeyChar e)]
    (if (= key \newline)
      (send-string->core e)
      (preview (pattern-in-rows (value input-for-string))))))

;(pattern-in-rows (value input-for-string))

(listen send-button :action send-string->core)
(listen input-for-string :key-typed keypress)


;----- Showing The UI
(show! pgm-window)

