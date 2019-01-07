(ns com.swayam.products.drutochithhi.addrstore.repo.RepoConfig
  (:require
    [omniconf.core :as cfg]
    [mount.core :refer [defstate] :as mount]
    )
  (:import (com.zaxxer.hikari HikariDataSource)
   )
)

(defn create-connection-pool
  [spec]
  (let [hikari-ds (doto (HikariDataSource.)
                    (.setDataSourceClassName (:classname spec))
                    (.setJdbcUrl (:url spec))
                    (.setUsername (:user spec))
                    (.setPassword (:password spec))
                    )]
    {:datasource hikari-ds}))

(defn create-address-store-datasource
  []
  (let [
        db-config
				  {:driver (cfg/get :database-driver)
				   :url (cfg/get :database-url)
				   :user (cfg/get :database-user)
				   :password (cfg/get :database-password)}
        ]
    (println "db-config")
    (create-connection-pool db-config)
    )
  )

(defstate address-store-db  
  :start (create-address-store-datasource)  
  :stop (.close (address-store-db :datasource))
  )
