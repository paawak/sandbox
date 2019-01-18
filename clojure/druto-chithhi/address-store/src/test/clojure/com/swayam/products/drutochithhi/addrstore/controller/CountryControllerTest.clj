(ns com.swayam.products.drutochithhi.addrstore.controller.CountryControllerTest
  (:require [clojure.test :refer :all]
            [io.pedestal.test :refer :all]
            [io.pedestal.http :as http]
            [com.swayam.products.drutochithhi.addrstore.config.StartupConfig :as startUp]
            [com.swayam.products.drutochithhi.addrstore.HttpService :as httpService]
            [clojure.tools.logging :as log]
            ))

(defn create-server
  []
  (::http/service-fn 
    (do
      (startUp/init-on-startup)
      (http/create-servlet (httpService/service))
    )
    )
  )

(defn print-map
  [map]
  (let [keys (keys map)]
    (doseq [key keys]
      (log/info "-----\n\tName:" key "\n\tValue: " (get map key))
      )
    )
  )

(deftest list-countries-test
  (let [ server (create-server) response (response-for server :get "/country")
         body (:body response)
         headers (:headers response)
         contentType (get headers "Content-Type")
        ]
    (log/info "printing all headers...")
    (print-map headers)
	  (is (=
	       body
	       "[{\"id\":1,\"name\":\"India\",\"shortname\":\"IN\",\"active\":true}]"
        ))
	  (is (=
	       contentType
	       "application/json;charset=UTF-8"))
   )
  )

(deftest add-new-country-form-data-test
  (let [ server (create-server) response (response-for server :post "/country" 
                     :body "name=South Africa&shortname=SF"
                     :headers {"Content-Type" "application/x-www-form-urlencoded"}
                   )
         body (:body response)
         headers (:headers response)
         contentType (get headers "Content-Type")
        ]
    (log/info "printing all headers...")
    (print-map headers)
	  (is (=
	       body
	       "{\"id\":2}"
        ))
	  (is (=
	       contentType
	       "application/json;charset=UTF-8"))
   )
  )

(deftest add-new-country-json-data-test
  (let [ server (create-server) response (response-for server :post "/country" 
                     :body "{\"name\":\"Finland\", \"shortname\":\"FN\"}"
                     :headers {"Content-Type" "application/json"}
                   )
         body (:body response)
         headers (:headers response)
         contentType (get headers "Content-Type")
        ]
    (log/info "printing all headers...")
    (print-map headers)
	  (is (=
	       body
	       "{\"id\":3}"
        ))
	  (is (=
	       contentType
	       "application/json;charset=UTF-8"))
   )
  )

(deftest add-new-country-bad-data-no-content-type-test
  (let [ server (create-server) response (response-for server :post "/country" )
         body (:body response)
         headers (:headers response)
         contentType (get headers "Content-Type")
        ]
    (log/info "printing all headers...")
    (print-map headers)
	  (is (=
	       body
	       "{\"error\":\"Bad request: Unsupported media type\"}"
        ))
	  (is (=
	       contentType
	       "application/json;charset=UTF-8"))
   )
  )

;; run the tests in the correct order
(defn test-ns-hook []
  (list-countries-test)
  (add-new-country-form-data-test)
  (add-new-country-json-data-test)
  (add-new-country-bad-data-no-content-type-test)
  )
