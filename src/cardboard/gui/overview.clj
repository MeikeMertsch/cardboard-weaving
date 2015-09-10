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

(def overview-panel
  (grid-panel :columns 10
              :vgap 10
              :hgap 10))

(def add-button (button :text "+"))

(defn- font []
  (config overview-window :title))

(defn- paint-canvas [character character-canvas]
  (bc/render-from-content character-canvas overview-size character)
  (config! character-canvas :size (s/screen-size overview-size character))
  character-canvas)

(defn- character-canvases [characters]
  (for [character characters
        :let [character-canvas (bc/bitmap-canvas)]]
    (paint-canvas character character-canvas)))

(defn- characters-and-add []
  (->> (character-canvases (sort (f/available-chars)))
       vec
       (#(conj % add-button))))

(defn- add-interactions []
  (if (not= (font) default-font)
    (for [canvas (config overview-panel :items)]
      (listen canvas :mouse-clicked (fn [_] (che/open (font) (:content (user-data canvas))))))
    (config! overview-panel :items (characters-and-add))))

(defn- render-overview-panel []
  (config! overview-panel :items (character-canvases (sort (f/available-chars))))
  (add-interactions))

(defn- reload [_]
  (render-overview-panel)
  (config! overview-window :content (scrollable overview-panel))
  (pack! overview-window))

(defn- add-character [_]
  (ac/open-preferences (font)))

(defn render [font]
  (config! overview-window :title font)
  (reload overview-window)
  (show! overview-window))

(listen add-button :action add-character)
(listen overview-window :window-activated reload)