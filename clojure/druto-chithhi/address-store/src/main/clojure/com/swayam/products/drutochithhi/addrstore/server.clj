(ns com.swayam.products.drutochithhi.addrstore.server
  (:gen-class) ; for -main method in uberjar
  (:require [io.pedestal.http :as server]
            [io.pedestal.http.route :as route]
            [clojure.tools.logging :as log]
            [com.swayam.products.drutochithhi.addrstore.HttpService :as httpService]
            [com.swayam.products.drutochithhi.addrstore.config.StartupConfig :as startUp]
            ))

;; This is an adapted service map, that can be started and stopped
;; From the REPL you can call server/start and server/stop on this service
(defn runnable-service
  []  (server/create-server (httpService/service))
  )

(defn run-dev
  "The entry-point for 'lein run-dev'"
  [& args]
  (println "\nCreating your [DEV] server...")
  (-> (httpService/service) ;; start with production configuration
      (merge {:env :dev
              ;; do not block thread that starts web server
              ::server/join? false
              ;; Routes can be a function that resolve routes,
              ;;  we can use this to set the routes to be reloadable
              ::server/routes #(route/expand-routes (deref #'httpService/all-routes))
              ;; all origins are allowed in dev mode
              ::server/allowed-origins {:creds true :allowed-origins (constantly true)}
              ;; Content Security Policy (CSP) is mostly turned off in dev mode
              ::server/secure-headers {:content-security-policy-settings {:object-src "'none'"}}})
      ;; Wire up interceptor chains
      server/default-interceptors
      server/dev-interceptors
      server/create-server
      server/start))

(defn -main
  "The entry-point for 'lein run'"
  [& args]
  (log/info "Creating your server...")
  (startUp/init-on-startup)
  (log/info "Routes defined: " httpService/all-routes)
  (server/start (runnable-service)))

