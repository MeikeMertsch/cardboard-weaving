(ns cardboard.gui.add-character
  (require [seesaw.core :refer :all]
           [cardboard.saving :as sav]
           [cardboard.gui.character-editing :as ce]
           [cardboard.constants :refer :all]))


(def input-for-character (text))
(def input-for-width (text))
(def this-font (atom default-font))
(def width-panel (grid-panel :columns 2
                             :items [character-label-text input-for-character
                                     width-label-text input-for-width]))

(defn- current-font []
  (deref this-font))

(defn- default-pattern []
  (repeat default-height (repeat (bigint (value input-for-width)) background-pixel)))

(defn- ok-function [_]
  (let [character (first (value input-for-character))]
    (sav/save-character character (default-pattern) (current-font))
    (ce/open (current-font) character)))

(defn- handle-character-changed [_]
  (if (> (count (value input-for-character)) 1)
    (config! input-for-character :text (str (last (value input-for-character))))))

(defn- handle-width-changed [caller]
  (let [key (.getKeyChar caller)]
    (if (not (re-matches #"\d+" (str key)))
      (config! input-for-width :text (re-find #"\d+" (value input-for-width)))
      (if (= key \newline)
        (ok-function caller)))))

(defn- ac-window []
  (dialog
    :title add-character-title
    :content width-panel
    :option-type :ok-cancel
    :success-fn ok-function
    :cancel-fn dispose!
    :width 240
    :height 150))

(defn open [font]
  (reset! this-font font)
  (show! (ac-window)))

(listen input-for-character :key handle-character-changed)
(listen input-for-width :key handle-width-changed)
