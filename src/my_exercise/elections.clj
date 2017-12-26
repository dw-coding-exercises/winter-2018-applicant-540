(ns my-exercise.elections
  (:require [hiccup.page :refer [html5]]
            [clojure.data.json :as json]
            [clj-time.core :as t]
            [clj-time.format :as f]))

;function to display the upcoming election to the user. If I had more time I would explore how to render more info about the election on the page based on the API. At the moment I am unsure what the pattern is for the nested info in the Election API response. The two responses coming back from Oregon and Virginia seem to store information slightly differently

(defn upcoming-elections [election]
  (def description (:description (get (json/read-str election :key-fn keyword) 0)))
  (def website (:website (get (json/read-str election :key-fn keyword) 0)))
  (def registration-info (:registration (:instructions (get (:voter-registration-methods (get (:district-divisions (get (json/read-str election :key-fn keyword)0))0))0))))
  (def date (:date (get (json/read-str election :key-fn keyword)0)))
  (def day (t/day (f/parse date)))
  (def month (t/month (f/parse date)))
  (def year (t/year (f/parse date)))
  
  (html5
    [:head
     [:meta {:charset "UTF-8"}]
     [:meta {:name "viewport"
             :content "width=device-width, initial-scale=1.0, maximum-scale=1.0"}]
     [:title "Find my next election"]
     [:link {:rel "stylesheet" :href "default.css"}]]
    [:div {:class "election-info"} ;basic styling is in default.css
      [:h2 "Your Upcoming Elections"]
      [:h4 description " on " month "-" day "-" year]
      [:h5 registration-info]
    ]))