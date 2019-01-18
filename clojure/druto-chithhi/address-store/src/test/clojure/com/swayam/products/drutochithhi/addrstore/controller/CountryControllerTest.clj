(ns com.swayam.products.drutochithhi.addrstore.controller.CountryControllerTest
  (:require [clojure.test :refer :all]
            [io.pedestal.test :refer :all]
            [io.pedestal.http :as bootstrap]
            [io.pedestal.http :as server]
            [com.swayam.products.drutochithhi.addrstore.config.StartupConfig :as startUp]
            [com.swayam.products.drutochithhi.addrstore.HttpService :as httpService]
            [clojure.tools.logging :as log]
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
         contentType (get headers "Content-Type")
        ]
    (log/info "********* " headers)
	  (is (=
	       body
	       "[{\"id\":1,\"name\":\"India\",\"shortname\":\"IN\",\"active\":true}]"
        ))
	  (is (=
	       contentType
	       "application/json;charset=UTF-8"))
   )
  )


