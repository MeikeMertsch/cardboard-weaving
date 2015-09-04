(ns cardboard.overview
  (:require [seesaw.core :refer :all]
            [cardboard.bitmap-canvas :as bc]
            [cardboard.character-editing :as che]
            [cardboard.font :as c]
            [cardboard.constants :refer :all]
            [cardboard.size :as s]))

(def overview-window
  (frame :title overview-title))

(defn open-character [character _]
  (che/open character))

(defn paint-canvas [character character-canvas]
  (bc/render character-canvas overview-size character)
  (config! character-canvas :size (s/screen-size overview-size character))
  (listen character-canvas :mouse-clicked (partial open-character character))
  character-canvas)

(defn character-canvases [characters]
  (for [character characters
        :let [character-canvas (bc/bitmap-canvas)]]
    (paint-canvas character character-canvas)))

(defn overview-panel []
  (scrollable (grid-panel :columns 10
                          :vgap 10
                          :hgap 10
                          :items (character-canvases (sort (c/available-chars))))))

(defn reload [_]
  (config! overview-window :content (overview-panel))
  (pack! overview-window))

(defn preview []
  (reload overview-window)
  (show! overview-window))

(listen overview-window :focus-gained reload)