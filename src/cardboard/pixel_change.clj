(ns cardboard.pixel-change
  (:require [cardboard.constants :refer :all]
            [cardboard.size :as s]))

(defn pattern-pixel-coords [[x y]]
  [(quot x (zoom-size :width))
   (quot y (zoom-size :height))])

(defn valid-coords? [[x y] pattern]
  (let [[width height] (s/pattern-size pattern)]
    (and (< -1 x width)
         (< -1 y height))))

(defn invert [filling]
  (if (= filling foreground-pixel)
    background-pixel
    foreground-pixel))

(defn current-filling [[x y] pattern]
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
  (let [coords (pattern-pixel-coords location)]
    (if (valid-coords? coords pattern)
      (->> (current-filling coords pattern)
           invert
           (exchange-pixel coords pattern))
      pattern)))
