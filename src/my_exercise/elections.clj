(ns my-exercise.elections
  (:require [hiccup.page :refer [html5]]))

(defn upcoming-elections [elections]
  (html5
    [:h2 "Your Upcoming Elections"]
    [:p elections]))