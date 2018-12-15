(ns client-account.country
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [ring.util.response :as ring-resp]))

(defn list-countries
  [request]
  (ring-resp/response "List countries"))

(defn add-new-country
  [request]
  (let [formParams (get (select-keys request [:form-params]) :form-params) name (get formParams :name) shortName (get formParams :shortname)]
    (println name "--" shortName)
    )
  (ring-resp/response "Add new country"))

(defn modify-country
  [request]
  (ring-resp/response "Modify country"))

