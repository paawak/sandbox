(ns com.swayam.products.drutochithhi.addrstore.controller.CountryControllerTest
  (:require [clojure.test :refer :all]
            [io.pedestal.test :refer :all]
            [io.pedestal.http :as http]
            [clojure.java.jdbc :as jdbc]
            [clojure.tools.logging :as log]
            [com.swayam.products.drutochithhi.addrstore.config.StartupConfig :as startUp]
            [com.swayam.products.drutochithhi.addrstore.HttpService :as httpService]
            [com.swayam.products.drutochithhi.addrstore.config.RepoConfig :refer [address-store-db]]
            ))

(def server
  (::http/service-fn 
    (do
      (startUp/init-on-startup)
      (http/create-servlet (httpService/service))
    )
    )
  )

(use-fixtures :each 
      (fn [test-case]
        (log/info "************************** running test case...")
        (test-case)
        (log/info "************************** cleanup after test...")
        (jdbc/delete! address-store-db :country [])
        (jdbc/execute! address-store-db ["alter sequence SEQ_COUNTRY restart with 1"])
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
  (jdbc/execute! address-store-db ["INSERT INTO country (name, shortname ) VALUES ('India', 'IN')"])
  (let [ response (response-for server :get "/country")
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
  (let [ response (response-for server :post "/country" 
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
	       "{\"id\":1}"
        ))
	  (is (=
	       contentType
	       "application/json;charset=UTF-8"))
   )
  )

(deftest add-new-country-json-data-test
  (let [ response (response-for server :post "/country" 
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
	       "{\"id\":1}"
        ))
	  (is (=
	       contentType
	       "application/json;charset=UTF-8"))
   )
  )

(deftest add-new-country-bad-data-no-content-type-test
  (let [ response (response-for server :post "/country" )
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

