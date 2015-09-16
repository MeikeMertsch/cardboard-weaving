(ns cardboard.gui.add-character
  (require [seesaw.core :refer :all]
           [cardboard.saving :as sav]
           [cardboard.font :as f]
           [cardboard.gui.character-editing :as ce]
           [cardboard.constants :refer :all]))


(def input-for-character (text))
(def input-for-width (text))
(def cancel-button (button :text cancel-button-text))
(def ok-button (button :text ok-button-text))
(def this-font (atom default-font))
(def dialog-panel (grid-panel :columns 2
                             :items [character-label-text input-for-character
                                     width-label-text input-for-width]))

(defn- current-font []
  (deref this-font))

(defn- default-pattern []
  (repeat default-height (repeat (bigint (value input-for-width)) background-pixel)))

(defn- add-character [dialog]
  (let [character (first (value input-for-character))]
    (sav/save-character character (default-pattern) (current-font))
    (ce/open (current-font) character)
    (dispose! dialog)))

(defn- character-is-new? []
  (not (contains? (f/available-chars) (first (value input-for-character)))))

(defn- both-fields-filled? []
  (and (not-empty (value input-for-character))
       (not-empty (value input-for-width))))

(defn- ok-function [window]
  (if (and (both-fields-filled?)
           (character-is-new?))
    (add-character window)))

(defn- handle-character-changed [_]
  (if (> (count (value input-for-character)) 1)
    (config! input-for-character :text (str (last (value input-for-character))))))

(defn- handle-width-changed [_]
  (if (not (re-matches digits (value input-for-width)))
    (config! input-for-width :text (re-find digits (value input-for-width)))))

(defn key-pressed-in-width [caller]
  (if (= (.getKeyChar caller) \newline)
    (ok-function caller)
    (handle-width-changed caller)))

(defn- ac-window []
  (dialog
    :title add-character-title
    :content dialog-panel
    :options [ok-button
              cancel-button]
    :width 240
    :height 150))

(defn open [font]
  (reset! this-font font)
  (show! (ac-window)))

(listen input-for-character :key handle-character-changed)
(listen input-for-character :mouse-motion handle-character-changed)
(listen input-for-width :key key-pressed-in-width)
(listen input-for-width :mouse-motion handle-width-changed)
(listen cancel-button :action dispose!)
(listen ok-button :action ok-function)