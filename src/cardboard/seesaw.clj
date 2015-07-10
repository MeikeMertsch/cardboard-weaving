(ns cardboard.seesaw
  (:gen-class)
  (:require [seesaw.core :as seesaw]))

(def window
  (seesaw/frame
    :title "First Example"
    :content "hello world"
    :width 200
    :height 50))

(seesaw/show! window)



