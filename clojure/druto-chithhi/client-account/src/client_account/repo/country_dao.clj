(ns client_account.repo.country_dao
  (:require [clojure.java.jdbc :as jdbc]
            [client_account.model.country_model :as model]
  )
  (:import [client_account.model.country_model Country])
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


