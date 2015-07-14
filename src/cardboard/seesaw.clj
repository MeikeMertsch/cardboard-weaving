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
(def preview-canvas (canvas :paint nil))
(def form-for-saving (grid-panel :columns 2
                                 :items [input-for-string
                                         send-button]))
(def preview-panel (horizontal-panel :items [form-for-saving
                                             preview-canvas]
                                     :size [900 :by 300]))
(def main-panel (vertical-panel :items [form-for-saving
                                        preview-panel]))
(def pgm-window
  (frame
    :title "Cardboard Weaving Patterns"
    :content main-panel
    :width 900))

(def style-filled (sg/style :background (scolor/color :black)))
(def style-unfilled (sg/style :background nil))

;----- Preview
(defn cell-state [cell]
  (if (= cell ".")
      style-filled
      style-unfilled))

(defn row-rectangles [row row-number]
  (for [column-number (range (count row))
        :let [cell (nth row column-number)]
        :let [rectangle (sg/rect (* 4 column-number) (* 2 row-number) 4 2)]
        :let [style (cell-state cell)]]
    [rectangle style]))

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

(defn handle-string-changed [caller]
  (->> (value caller)
       pattern-in-rows
       preview))

;----- Submitting The String
(defn send-string->core [file action]
  (save-instructions-for (value input-for-string) file)
  (alert action "Thanks!\nYou saved your pattern"))

(defn handle-submit [action]
  (->> (choose-file :type :save)
       (#(if (not (nil? %))
          (send-string->core % action)))))

(defn keypress [caller]
  (let [key (.getKeyChar caller)]
    (if (= key \newline)
      (handle-submit caller))))

;----- Listeners
(listen send-button :action handle-submit)
(listen input-for-string :key-pressed keypress)
(listen input-for-string :key handle-string-changed)

;----- Showing The UI
(show! pgm-window)
