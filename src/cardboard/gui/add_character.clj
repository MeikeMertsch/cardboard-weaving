(ns cardboard.gui.add-character
  (require [seesaw.core :refer :all]
           [seesaw.dev :refer :all]
           [cardboard.saving :as sav]
           [cardboard.gui.character-editing :as ce]
           [cardboard.constants :refer :all]))


(def input-for-character (text))
(def input-for-width (text))
(def width-panel (grid-panel :columns 2
                             :items ["Character"
                                     input-for-character
                                     "Width"
                                     input-for-width]))

(defn- default-pattern []
  (repeat 17 (repeat (bigint (value input-for-width)) background-pixel)))

(defn- ok-function [_]
  (let [character (first (value input-for-character))]
    (sav/save-character character (default-pattern) "custom")
    (ce/open "custom" character))
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


