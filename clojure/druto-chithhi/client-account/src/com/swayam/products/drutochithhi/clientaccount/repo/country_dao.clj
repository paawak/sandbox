(ns com.swayam.products.drutochithhi.clientaccount.repo.country_dao
  (:require [clojure.java.jdbc :as jdbc]
            [com.swayam.products.drutochithhi.clientaccount.model.country_model :as model]
  )
  (:import [com.swayam.products.drutochithhi.clientaccount.model.country_model Country])
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


