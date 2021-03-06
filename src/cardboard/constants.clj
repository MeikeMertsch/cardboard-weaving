(ns cardboard.constants)

(def empty-string "")
(def empty-pattern '(()))
(def letter-space "0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0")

(def new-line "\n")
(def dash "-")
(def space " ")
(def header "Pattern for")

(def default-text "Text to be turned into a pattern")
(def save-button-text "Save instructions")
(def overview-button-text "Edit font")
(def app-title "Cardboard Weaving Patterns")
(def overview-title "Font overview")
(def saved-instructions-text "Thanks!\nYou saved your pattern")
(def foreground-pixel ".")
(def background-pixel "0")
(def instruction-extension ".txt")
(def invalid-characters-message "The following invalid characters will be ignored: ")

(def preview-size {:width 3 :height 2})
(def overview-size {:width 9 :height 6})
(def zoom-size {:width 30 :height 20})

(def save-character-button-text "Save")
(def cancel-button-text "Cancel")
(def ok-button-text "OK")

(def character-editing-title "Edit character")
(def default-character-location "resources/default/")
(def font-location "resources/")
(def path-separator "/")
(def default-font "default")
(def character-extension ".ch")
(def add-button-text "+")
(def delete-button-text "-")

(def character-label-text "Character")
(def width-label-text "Width")
(def default-height 17)
(def add-character-title "Add character")
(def character-exists-error "This character exists already in this font!")

(def digits #"\d+")