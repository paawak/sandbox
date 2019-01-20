(ns com.swayam.products.drutochithhi.addrstore.controller.LandingPageControllerIntegrationTest
  (:require [clojure.test :refer :all]
            [io.pedestal.test :refer :all]
            [io.pedestal.http :as http]
            [com.swayam.products.drutochithhi.addrstore.config.StartupConfig :as startUp]
            [com.swayam.products.drutochithhi.addrstore.HttpService :as httpService]
            ))

(def server
  (::http/service-fn 
    (do
      (startUp/init-on-startup)
      (http/create-servlet (httpService/service))
    )
    )
  )

(deftest home-page-test
  (let [ response (response-for server :get "/")]
	  (is (=
	       (:body response)
	       "Hello World!"))
	  (is (=
	       (:headers response)
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

(deftest about-page-test
  (let [ response (response-for server :get "/about")]
  (is (.contains
       (:body response)
       "Clojure 1.9"))
  (is (=
       (:headers response)
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

