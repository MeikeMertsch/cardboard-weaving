(ns cardboard.gui.overview
  (:require [seesaw.core :refer :all]
            [cardboard.gui.bitmap-canvas :as bc]
            [cardboard.gui.character-editing :as che]
            [cardboard.gui.add-character :as ac]
            [cardboard.font :as f]
            [cardboard.constants :refer :all]
            [cardboard.size :as s]))

(def overview-window
  (frame :title overview-title))

(def add-button (button :text "+"))

(defn font []
  (config overview-window :title))

(defn paint-canvas [character character-canvas]
  (bc/render character-canvas overview-size character)
  (config! character-canvas :size (s/screen-size overview-size character))
  (if (not= (font) default-font)
    (listen character-canvas :mouse-clicked (fn [_] (che/open (font) character))))
  character-canvas)

(defn character-canvases [characters]
  (for [character characters
        :let [character-canvas (bc/bitmap-canvas)]]
    (paint-canvas character character-canvas)))

(defn characters-and-add []
  (->> (character-canvases (sort (f/available-chars)))
       vec
       (#(conj % add-button))))

(defn overview-panel []
  (scrollable (grid-panel :columns 10
                          :vgap 10
                          :hgap 10
                          :items (characters-and-add))))

(defn reload [_]
  (config! overview-window :content (overview-panel))
  (pack! overview-window))

(defn render [font]
  (reload overview-window)
  (config! overview-window :title font)
  (show! overview-window))

(defn add-character [_]
  (ac/open-preferences (font)))

(listen overview-window :focus-gained reload)
(listen add-button :action add-character)