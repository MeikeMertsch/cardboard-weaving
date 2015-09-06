(ns cardboard.gui.add-character
  (require [seesaw.core :refer :all]
           [seesaw.dev :refer :all]
           [cardboard.constants :refer :all]))


(def input-for-character (text))
(def input-for-width (text))
(def width-panel (grid-panel :columns 2
                             :items ["Character"
                                     input-for-character
                                     "Width"
                                     input-for-width]))

(defn ok-function [_]
  (alert "yeay"))

(def ac-window
  (dialog
    :title "Add character"
    :content width-panel
    :option-type :ok-cancel
    :success-fn ok-function
    :width 240
    :height 150))

(defn open-preferences [_]
  (show! ac-window))


