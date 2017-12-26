(ns my-exercise.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.reload :refer [wrap-reload]]
            [my-exercise.home :as home]
            [my-exercise.elections :as elections]
            [my-exercise.helpers :as helpers]))

(defroutes app
  (GET "/" [] home/page)
  (POST "/search"
    {params :params}

    (let [city (:city params)
          state (:state params)
          zip (:zip params)
          street (:street params)
          street2 (:street2 params)]
              
      ;builds request string from params sends to election API then passes the body of the response to the view
      (elections/upcoming-elections (:body (helpers/httpCall (helpers/assembleRequestString "https://api.turbovote.org/elections/upcoming?district-divisions=ocd-division/country:us/state:" city state))))))
  (route/resources "/")
  (route/not-found "Not found"))

(def handler
  (-> app
      (wrap-defaults site-defaults)
      wrap-reload))
