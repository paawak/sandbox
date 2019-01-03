(ns com.swayam.products.drutochithhi.clientaccount.repo.CountryDao
  (:require [clojure.java.jdbc :as jdbc]
            [com.swayam.products.drutochithhi.clientaccount.repo.RepoConfig :as config]
            [com.swayam.products.drutochithhi.clientaccount.model.Models :as models]
  )
  (:import [com.swayam.products.drutochithhi.clientaccount.model.Models Country])
 )


(defn add-new-country
  [country]
  ;; remove the id field before insert
  (jdbc/insert! config/datasource :country (dissoc country :id))
  )

(defn list-countries
  []
  (jdbc/query config/datasource ["SELECT * FROM COUNTRY WHERE ACTIVE=TRUE"])
  )

(defn get-country
  [countryId]
  (jdbc/query config/datasource ["SELECT * FROM COUNTRY WHERE ACTIVE=TRUE AND ID = ?" (read-string countryId)] {:result-set-fn first})
  )
