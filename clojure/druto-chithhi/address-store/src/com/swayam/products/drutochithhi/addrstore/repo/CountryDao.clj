(ns com.swayam.products.drutochithhi.addrstore.repo.CountryDao
  (:require [clojure.java.jdbc :as jdbc]
            [com.swayam.products.drutochithhi.addrstore.repo.RepoConfig :as config]
            [com.swayam.products.drutochithhi.addrstore.model.Models :as models]
            [com.swayam.products.drutochithhi.addrstore.config.Configs :as conf]
            [omniconf.core :as cfg]
  )
  (:import [com.swayam.products.drutochithhi.addrstore.model.Models Country])
 )


(defn add-new-country
  [country]
  ;; remove the id field before insert
  (jdbc/insert! config/datasource :country (dissoc country :id))
  )

(defn list-countries
  []
  (conf/load-configs)
  (println "database-url from config: " (cfg/get :database-url))
  (jdbc/query config/datasource ["SELECT * FROM COUNTRY WHERE ACTIVE=TRUE"])
  )

(defn get-country
  [countryId]
  (jdbc/query config/datasource ["SELECT * FROM COUNTRY WHERE ACTIVE=TRUE AND ID = ?" (read-string countryId)] {:result-set-fn first})
  )
