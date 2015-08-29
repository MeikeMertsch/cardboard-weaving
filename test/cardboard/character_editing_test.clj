(ns cardboard.character-editing-test
  (:require [expectations :refer :all]
            [cardboard.character-editing :refer :all]

            [seesaw.core :refer :all]
            [seesaw.graphics :as sg]
            [seesaw.color :as scol]
            [seesaw.mouse :as mou]
            [cardboard.preview-canvas :as pre]
            [cardboard.constants :refer :all]))


;;; Finding The Pixel Coordinations Of The Absolute Coordinations
(expect [1 2] (pixel-coords [40 40]))
(expect [1 0] (pixel-coords [58 8]))

