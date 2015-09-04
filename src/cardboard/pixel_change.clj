(ns cardboard.pixel-change
  (:require [cardboard.constants :refer :all]
            [cardboard.size :as s]))

(defn pattern-pixel-coords [[x y]]
  [(quot x (inc (zoom-size :width)))
   (quot y (inc (zoom-size :height)))])

(defn valid-coords? [[x y] pattern]
  (let [[width height] (s/pattern-size pattern)]
    (and (< -1 x width)
         (< -1 y height))))

(defn invert [filling]
  (if (= filling foreground-pixel)
    background-pixel
    foreground-pixel))

(defn exchange-pixel [[x y] pattern]
  (->> (fn [row] (update-in (vec row) [x] invert))
       (update-in (vec pattern) [y])))

(defn invert-pixel [location pattern]
  (let [coords (pattern-pixel-coords location)]
    (if (valid-coords? coords pattern)
        (exchange-pixel coords pattern)
      pattern)))
