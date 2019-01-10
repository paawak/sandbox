(ns com.swayam.products.drutochithhi.addrstore.controller.CountryController
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [ring.util.response :as ring-resp]
            [clojure.tools.logging :as log]
            [com.swayam.products.drutochithhi.addrstore.repo.CountryDao :as dao]
         )
  (:import (com.swayam.products.drutochithhi.addrstore.model.Models Country))
  )

(def interceptors [
                    (body-params/body-params) 
                    http/json-body
                    ]
  )

(def add-new-country-interceptor {
    :name ::add-new-country-interceptor
    :enter (fn [context]
             (log/info context)
             context
             )
   }
  )

(def country-routes #{
              ["/country/:id" :get (conj interceptors `get-country)]
              ["/country" :get (conj interceptors `list-countries)]
              ["/country" :post (conj interceptors add-new-country-interceptor `add-new-country)]
              ["/country" :put (conj interceptors `modify-country)]
           }
  )

(defn get-country
  [request]
  (let [
        countryId ((request :path-params) :id)
        ]
    (println "Trying to fetch country with id: " countryId)
    {:status 200 :body (dao/get-country countryId)}
    )
  )

(defn list-countries
  [request]
  {:status 200 :body (dao/list-countries)}
  )

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
        {:status 200 :body {:id newId}}
	      )
      )
    )
  )

(defn modify-country
  [request]
  (ring-resp/response "Modify country"))

