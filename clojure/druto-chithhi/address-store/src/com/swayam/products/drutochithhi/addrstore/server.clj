(ns com.swayam.products.drutochithhi.addrstore.server
  (:gen-class) ; for -main method in uberjar
  (:require [io.pedestal.http :as server]
            [io.pedestal.http.route :as route]
            [com.swayam.products.drutochithhi.addrstore.HttpService :as httpService]
            [com.swayam.products.drutochithhi.addrstore.config.StartupConfig :as startUp]
            ))

;; This is an adapted service map, that can be started and stopped
;; From the REPL you can call server/start and server/stop on this service
(defn runnable-service
  []  (server/create-server (httpService/service))
  )

(defn -main
  "The entry-point for 'lein run'"
  [& args]
  (println "\nCreating your server...")
  (startUp/init-on-startup)
  (println "\nRoutes defined: " httpService/all-routes)
  (server/start (runnable-service)))

