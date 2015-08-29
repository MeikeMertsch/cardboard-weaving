(ns cardboard.pixel-change
  (:require [cardboard.constants :refer :all]))

(defn pixel-coords [[x y]]
  [(quot x (zoom-size :width))
   (quot y (zoom-size :height))])

(defn invert-pixel [filling]
  (if (= filling foreground-pixel)
    background-pixel
    foreground-pixel))

(defn pixel-at [[x y] pattern]
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

(defn change-pixel [location pattern]
  (->> (pixel-at location pattern)
       invert-pixel
       (exchange-pixel location pattern)))