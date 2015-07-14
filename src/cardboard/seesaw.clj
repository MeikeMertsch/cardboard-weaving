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
                                         send-button
                                         preview-canvas]))

(def pgm-window
  (frame
    :title "Cardboard Weaving Patterns"
    :content form-for-saving
    :width 600))

(def style-filled (sg/style :background (scolor/color :black)))
(def style-unfilled (sg/style :background (scolor/color :white)))

;----- Actions
(defn send-string->core [file action]
  (save-instructions-for (value input-for-string) file)
  (alert action "Thanks!\nYou saved your pattern"))

(defn handle-enter [action]
  (->> (choose-file :type :save)
       (#(if (not (nil? %))
          (send-string->core % action)))))

(defn row-rectangles [row row-number]
  (for [column-number (range (count row))
        :let [cell (nth row column-number)]]
    (sg/rect (* 6 column-number) (* 4 row-number) 6 4)))

(defn rectangles [pattern]
  (->> (for [row-number (range (count pattern))
             :let [row (nth pattern row-number)]]
         (row-rectangles row row-number))
       (apply concat)))

(defn paint [rects c g]
  (doseq [rect rects]
    (sg/draw g rect style-filled)))

(defn preview [pattern-in-rows]
  (config! preview-canvas :paint #(paint (rectangles pattern-in-rows) %1 %2)))

(defn handle-string-changed [caller]
  (->> (value caller)
       pattern-in-rows
       preview))

(defn keypress [caller]
  (let [key (.getKeyChar caller)]
    (if (= key \newline)
      (handle-enter caller))))

(listen send-button :action handle-enter)
(listen input-for-string :key-pressed keypress)
(listen input-for-string :key handle-string-changed)


;----- Showing The UI
(show! pgm-window)

