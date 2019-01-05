(ns com.swayam.products.drutochithhi.addrstore.repo.RepoConfig
  (:require
    [omniconf.core :as cfg]
    )
  (:import (com.zaxxer.hikari HikariDataSource)
   )
)

(defn connection-pool
  [spec]
  (let [hikari-ds (doto (HikariDataSource.)
                    (.setDataSourceClassName (:classname spec))
                    (.setJdbcUrl (:url spec))
                    (.setUsername (:user spec))
                    (.setPassword (:password spec))
                    )]
    {:datasource hikari-ds}))

(defn datasource
  []
  (let [
        db-config
				  {:driver (cfg/get :database-driver)
				   :url (cfg/get :database-url)
				   :user (cfg/get :database-user)
				   :password (cfg/get :database-password)}
        ]
    (println "db-config")
    (connection-pool db-config)
    )
  )
