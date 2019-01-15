(ns com.swayam.products.drutochithhi.addrstore.repo.CountryDao
  (:require [clojure.java.jdbc :as jdbc]
            [com.swayam.products.drutochithhi.addrstore.model.Models :as models]
            [com.swayam.products.drutochithhi.addrstore.config.RepoConfig :refer [address-store-db]]
  )
  (:import [com.swayam.products.drutochithhi.addrstore.model.Models Country])
 )


(defn add-new-country
  [country]
  ;; remove the id field before insert
  (jdbc/insert! address-store-db :country (dissoc country :id))
  )

(defn list-countries
  []
  (jdbc/query address-store-db ["SELECT * FROM COUNTRY WHERE ACTIVE=TRUE"])
  )

(defn get-country
  [countryId]
  (jdbc/query address-store-db ["SELECT * FROM COUNTRY WHERE ACTIVE=TRUE AND ID = ?" (read-string countryId)] {:result-set-fn first})
  )
