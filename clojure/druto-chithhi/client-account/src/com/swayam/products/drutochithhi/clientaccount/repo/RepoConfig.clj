(ns com.swayam.products.drutochithhi.clientaccount.repo.RepoConfig
;  (:require [clojure.java.jdbc :as jdbc]
;  )
  (:import (com.zaxxer.hikari HikariDataSource)
   )
)

(def db-config
  {:driver "org.postgresql.Driver"
   :url "jdbc:postgresql://localhost:5432/druto-chithhi-client-account"
   :user "postgres"
   :password "postgres"})

(defn connection-pool
  [spec]
  (let [hikari-ds (doto (HikariDataSource.)
                    (.setDataSourceClassName (:classname spec))
                    (.setJdbcUrl (:url spec))
                    (.setUsername (:user spec))
                    (.setPassword (:password spec))
                    )]
    {:datasource hikari-ds}))

(def datasource (connection-pool db-config))
