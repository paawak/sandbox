(ns com.swayam.products.drutochithhi.clientaccount.controller.MainControllerTest
  (:require [clojure.test :refer :all]
            [io.pedestal.test :refer :all]
            [io.pedestal.http :as bootstrap]
            [com.swayam.products.drutochithhi.clientaccount.controller.MainController :as controller]))

(def service
  (::bootstrap/service-fn (bootstrap/create-servlet controller/service)))

(deftest home-page-test
  (is (=
       (:body (response-for controller :get "/"))
       "Hello World!"))
  (is (=
       (:headers (response-for controller :get "/"))
       {"Content-Type" "text/html;charset=UTF-8"
        "Strict-Transport-Security" "max-age=31536000; includeSubdomains"
        "X-Frame-Options" "DENY"
        "X-Content-Type-Options" "nosniff"
        "X-XSS-Protection" "1; mode=block"
        "X-Download-Options" "noopen"
        "X-Permitted-Cross-Domain-Policies" "none"
        "Content-Security-Policy" "object-src 'none'; script-src 'unsafe-inline' 'unsafe-eval' 'strict-dynamic' https: http:;"})))

(deftest about-page-test
  (is (.contains
       (:body (response-for controller :get "/about"))
       "Clojure 1.9"))
  (is (=
       (:headers (response-for controller :get "/about"))
       {"Content-Type" "text/html;charset=UTF-8"
        "Strict-Transport-Security" "max-age=31536000; includeSubdomains"
        "X-Frame-Options" "DENY"
        "X-Content-Type-Options" "nosniff"
        "X-XSS-Protection" "1; mode=block"
        "X-Download-Options" "noopen"
        "X-Permitted-Cross-Domain-Policies" "none"
        "Content-Security-Policy" "object-src 'none'; script-src 'unsafe-inline' 'unsafe-eval' 'strict-dynamic' https: http:;"})))

