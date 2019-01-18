(ns com.swayam.products.drutochithhi.addrstore.controller.CountryControllerTest
  (:require [clojure.test :refer :all]
            [io.pedestal.test :refer :all]
            [io.pedestal.http :as bootstrap]
            [io.pedestal.http :as server]
            [com.swayam.products.drutochithhi.addrstore.config.StartupConfig :as startUp]
            [com.swayam.products.drutochithhi.addrstore.HttpService :as httpService]
            ))

(def server
  (::bootstrap/service-fn 
    (do
      (startUp/init-on-startup)
      (bootstrap/create-servlet (httpService/service))
    )
    )
  )

(deftest list-countries-test
  (let [ response (response-for server :get "/country")
         body (:body response)
         headers (:headers response)
        ]
	  (is (=
	       body
	       [{
          :id 1
          :name "India"
          :shortname "IN"
          :active true
          }]
        ))
	  (is (=
	       headers
	       {"Content-Type" "text/html;charset=UTF-8"
	        "Strict-Transport-Security" "max-age=31536000; includeSubdomains"
	        "X-Frame-Options" "DENY"
	        "X-Content-Type-Options" "nosniff"
	        "X-XSS-Protection" "1; mode=block"
	        "X-Download-Options" "noopen"
	        "X-Permitted-Cross-Domain-Policies" "none"
	        "Content-Security-Policy" "object-src 'none'; script-src 'unsafe-inline' 'unsafe-eval' 'strict-dynamic' https: http:;"}))
   )
  )


