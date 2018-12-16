(ns com.swayam.products.drutochithhi.clientaccount.controller.CountryController
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [ring.util.response :as ring-resp]
            [com.swayam.products.drutochithhi.clientaccount.repo.CountryDao :as dao]
         )
  (:import (com.swayam.products.drutochithhi.clientaccount.model.Models Country))
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
	    (let [generatedKeys (dao/add-new-country newCountry) newId (get (first generatedKeys) :id)]
        (println "generatedKeys: " generatedKeys)
        (println "newId: " newId)
        (ring-resp/response (str newId))
	      )
      )
    )
  )

(defn modify-country
  [request]
  (ring-resp/response "Modify country"))

