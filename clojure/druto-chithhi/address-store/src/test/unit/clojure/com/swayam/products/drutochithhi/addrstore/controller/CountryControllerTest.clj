(ns com.swayam.products.drutochithhi.addrstore.controller.CountryControllerTest
  (:require [clojure.test :refer :all]
            [io.pedestal.test :refer :all]
            [io.pedestal.http :as http]
            [clojure.java.jdbc :as jdbc]
            [io.pedestal.interceptor.chain :as chain]
            [clojure.tools.logging :as log]
            [com.swayam.products.drutochithhi.addrstore.controller.CountryController :as testClass]
            ))


(deftest country-interceptor-test-no-content-type
  ;;http://clojuredocs.org/clojure.core/binding
  ;;http://clojuredocs.org/clojure.core/with-redefs
  ;;http://clojuredocs.org/clojure.core/with-redefs-fn
  (let [enterFunction (:enter testClass/country-interceptor)]
    (with-redefs-fn {#'chain/terminate (fn [context] (log/info "!!!!!!!! STUBBED **chain/terminate**") true)}
      #(is (= true (enterFunction {})))
      )
    )
  )
