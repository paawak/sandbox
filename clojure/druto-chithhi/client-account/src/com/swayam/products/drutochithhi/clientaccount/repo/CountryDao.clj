(ns com.swayam.products.drutochithhi.clientaccount.repo.CountryDao
  (:require [clojure.java.jdbc :as jdbc]
            [com.swayam.products.drutochithhi.clientaccount.model.countrymodel :as model]
  )
  (:import [com.swayam.products.drutochithhi.clientaccount.model.countrymodel Country])
 )


(def db-spec
  {:dbtype "postgres"
   :dbname "druto-chithhi-client-account"
   :user "postgres"
   :password "postgres"})

(defn add-new-country
  [country]
  (jdbc/insert! db-spec :country {:name (:name country) :shortname (:shortName country)})
  )


