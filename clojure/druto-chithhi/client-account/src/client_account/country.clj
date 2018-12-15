(ns client_account.country
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [ring.util.response :as ring-resp]
            [client_account.model.country_model :as model]
            [client_account.repo.country_dao :as dao]
         )
  (:import [client_account.model.country_model Country])
  )

(defn list-countries
  [request]
  (ring-resp/response "List countries"))

(defn add-new-country
  [request]
  (let [
        formParams (get (select-keys request [:form-params]) :form-params) 
        name (get formParams :name) 
        shortName (get formParams :shortname)]
    (println name "--" shortName)
    (let [newCountry (Country. nil name shortName)
          ]
      (println "trying to insert " newCountry "...")
;      (let ([generatedKeys (dao/add-new-country newCountry)]
;             (println "generatedKeys: " generatedKeys) 
;         ))

	    (let [generatedKeys (dao/add-new-country newCountry)]
        (println "generatedKeys: " generatedKeys)
	      )

      )

    )
  (ring-resp/response "Add new country"))

(defn modify-country
  [request]
  (ring-resp/response "Modify country"))

