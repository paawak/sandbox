(ns com.swayam.products.drutochithhi.clientaccount.repo.CountryDao
  (:require [clojure.java.jdbc :as jdbc]
            [com.swayam.products.drutochithhi.clientaccount.model.Models :as models]
  )
  (:import [com.swayam.products.drutochithhi.clientaccount.model.Models Country])
 )


(def db-spec
  {:dbtype "postgres"
   :dbname "druto-chithhi-client-account"
   :user "postgres"
   :password "postgres"})

(defn add-new-country
  [country]
  ;; remove the id field before insert
  (jdbc/insert! db-spec :country (dissoc country :id))
  )


