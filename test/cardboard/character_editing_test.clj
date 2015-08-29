(ns cardboard.character-editing-test
  (:require [expectations :refer :all]
            [cardboard.character-editing :refer :all]))


;;; Finding The Pixel Coordinations Of The Absolute Coordinations
(expect [1 2] (pixel-coords [40 40]))
(expect [1 0] (pixel-coords [58 8]))

