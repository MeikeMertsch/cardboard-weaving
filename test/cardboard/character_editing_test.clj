(ns cardboard.character-editing-test
  (:require [expectations :refer :all]
            [cardboard.character-editing :refer :all]
            [cardboard.constants :refer :all]))


;;; Finding The Pixel Coordinations Of The Absolute Coordinations
(expect [1 2] (pixel-coords [40 40]))
(expect [1 0] (pixel-coords [58 8]))


;;; Exchanging The Color Of The Pixel

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

(expect '(("1" "2") ("." "4") ("5" "6")) (change-pixel [0 1] '(("1" "2") ("0" "4") ("5" "6"))))