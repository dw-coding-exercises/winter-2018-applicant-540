(ns my-exercise.helpers
  (:require
            [clj-http.client :as client]
            [clojure.data.json :as json]
            [clojure.string :as str]))

(defn lowerCaseItem
  [item]
  (str/join "" (map str/lower-case item)))

(defn replaceSpacesWithUnderscores 
  [city]

  (str/replace city #" " "_"))

; assembling request string, determines if there is a city and state to request or just state

; to improve the quality of the request string I could add in another http call to an address API service that could give me the county from the inputted address. I would then add in the county to the request string 
(defn assembleRequestString 
    [url city state]
    (if (= city "") (str url (lowerCaseItem state)) (str url (lowerCaseItem state) "/place:" (replaceSpacesWithUnderscores (lowerCaseItem city)))))

;sends http call using clj-http
(defn httpCall
  [url]
  (client/get url {:accept :json}))

