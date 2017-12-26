(ns my-exercise.helpers
  (:require
            [clj-http.client :as client]
            [clojure.data.json :as json]
            [clojure.string :as str]
            ))

(defn lowerCaseItem
  [item]
  (str/join "" (map str/lower-case item)))

(defn replaceSpacesWithUnderscores 
  [city]

  (str/replace city #" " "_"))

(defn assembleRequestString 
    [url city state]
    (if (= city "") (str url (lowerCaseItem state)) (str url (lowerCaseItem state) "/place:" (replaceSpacesWithUnderscores (lowerCaseItem city)))))