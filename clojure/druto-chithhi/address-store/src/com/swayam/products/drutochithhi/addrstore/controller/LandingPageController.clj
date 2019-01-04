(ns com.swayam.products.drutochithhi.addrstore.controller.LandingPageController
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [ring.util.response :as ring-resp]
          ))

(defn about-page
  [request]
  (ring-resp/response (format "Clojure %s - served from %s"
                              (clojure-version)
                              (route/url-for ::about-page))))

(defn home-page
  [request]
  (ring-resp/response "Hello World!"))

(def common-interceptors [
                          (body-params/body-params) 
                          http/html-body
                          ])

(def landing-page-routes #{["/" :get (conj common-interceptors `home-page)]
                           ["/about" :get (conj common-interceptors `about-page)]
           })


