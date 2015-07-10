(ns cardboard.seesaw
  (:gen-class)
  (:require [seesaw.core :as seesaw])
  (:require [seesaw.font :as font])
  (:require [cardboard.font :refer :all]))

(seesaw/native!)

(def hagrid-string (apply str (interpose "\n" (concat (map #(apply str %) (pattern-of "Hagrid"))))))

(def mytext (seesaw/text :id :hagrid :text hagrid-string :multi-line? true :font (font/font "Courier")))

(def window
  (seesaw/frame
    :title "Cardboard Weaving Patterns"
    :content mytext
    :width 400
    :height 600))

(seesaw/show! window)

