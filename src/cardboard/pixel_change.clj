(ns cardboard.pixel-change
  (:require [cardboard.constants :refer :all]))

(defn pixel-coords [[x y]]
  [(quot x (zoom-size :width))
   (quot y (zoom-size :height))])

(defn invert [filling]
  (if (= filling foreground-pixel)
    background-pixel
    foreground-pixel))

(defn pixel-filling [[x y] pattern]
  (nth (nth pattern y) x))

(defn exchange-row-pixel [x row filling]
  (->> row
       (split-at x)
       (#(list (concat (first %)
                       (list filling)
                       (rest (last %)))))))

(defn exchange-pixel [[x y] pattern filling]
  (->> (split-at y pattern)
       (#(concat (first %)
                 (exchange-row-pixel x (first (last %)) filling)
                 (rest (last %))))))

(defn invert-pixel [location pattern]
  (let [coords (pixel-coords location)]
    (->> (pixel-filling coords pattern)
         invert
         (exchange-pixel coords pattern))))