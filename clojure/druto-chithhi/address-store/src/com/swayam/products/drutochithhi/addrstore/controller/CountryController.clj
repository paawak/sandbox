(ns com.swayam.products.drutochithhi.addrstore.controller.CountryController
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.interceptor.chain :as chain]
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

(defn extract-country
  [requestParam]
  (let [
        id (get requestParam :id)
        name (get requestParam :name) 
        shortName (get requestParam :shortname)]
    (Country. id name shortName)
    )  
  )

(def country-interceptor {
    :name ::country-interceptor
    :enter (fn [context]
             (let [
                   request (get context :request)
                   ]
                            (if-not (contains? request :content-type)
							               (do (log/info "The content-type is not specified")
							               (chain/terminate context))
								             (let [contentType (get request :content-type)]
								               (cond (= contentType "application/x-www-form-urlencoded") 
                                     (do 
                                       (log/info "The content type is" contentType)
                                       (update context :request assoc :country (extract-country (get request :form-params)))
                                       ) 
								                     (= contentType "application/json") 
                                     (do 
                                       (log/info "The content type is" contentType)
                                       (update context :request assoc :country (extract-country (get request :json-params)))
                                       )
								                     :else (do
								                             (log/info "Un-supported content type: " contentType)
								                             (chain/terminate context)
								                             )
								                 )
								               )
							             )
               )
             )
    :leave (fn [context]
             (let [request (get context :request)] 
               (if-not (contains? request :country)
                 (do(log/info "Request terminated successfully")
                   (assoc context :response {:status 415 :body "{'error': 'Bad request: Unsupported media type'}"})
                   )
                 context
                 )
               )
             )
   }
  )

(def country-routes #{
              ["/country/:id" :get (conj interceptors `get-country)]
              ["/country" :get (conj interceptors `list-countries)]
              ["/country" :post (conj interceptors country-interceptor `add-new-country)]
              ["/country" :put (conj interceptors `modify-country)]
           }
  )

(defn get-country
  [request]
  (let [
        countryId ((request :path-params) :id)
        ]
    (log/debug "Trying to fetch country with id: " countryId)
    {:status 200 :body (dao/get-country countryId)}
    )
  )

(defn list-countries
  [request]
  {:status 200 :body (dao/list-countries)}
  )

(defn add-new-country
  [request]
  (let [newCountry (get request :country)
            ]
        (log/debug "trying to insert " newCountry "...")
	      (let [generatedKeys (dao/add-new-country newCountry) newId (get (first generatedKeys) :id)]
          (log/debug "generatedKeys: " generatedKeys)
          (log/debug "newId: " newId)
          {:status 200 :body {:id newId}}
	        )
        )
  )


(defn modify-country
  [request]
  (ring-resp/response "Modify country"))

