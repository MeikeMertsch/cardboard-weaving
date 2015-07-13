(ns cardboard.seesaw
  (:gen-class)
  (:require [seesaw.core :refer :all])
  (:require [seesaw.chooser :refer :all])
  (:require [cardboard.core :refer :all]))

(native!)

(def input-for-string (text "Text to be turned into a pattern"))

(defn send-string->core [action]
  (->> (choose-file :type :save)
       (save-instructions-for (value input-for-string)))
  (alert action "Thanks!\nYou saved your pattern"))

(def send-button (button :text "Generate pattern"))

(defn keypress [e]
  (let [k (.getKeyChar e)]
    (if (= k \newline)
      (send-string->core e))))

(listen send-button :action send-string->core)
(listen input-for-string :key-typed keypress)

(def form-for-saving (grid-panel :columns 2
    :items [input-for-string
            send-button]))

(def pgm-window
  (frame
    :title "Cardboard Weaving Patterns"
    :content form-for-saving
    :width 600))

(show! pgm-window)

