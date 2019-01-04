(ns com.swayam.products.drutochithhi.addrstore.repo.RepoConfig
  (:import (com.zaxxer.hikari HikariDataSource)
   )
)

(def db-config
  {:driver "org.postgresql.Driver"
   :url "jdbc:postgresql://192.168.1.4:5432/druto-chithhi-client-account"
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