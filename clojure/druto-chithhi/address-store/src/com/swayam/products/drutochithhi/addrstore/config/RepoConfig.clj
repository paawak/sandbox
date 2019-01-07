(ns com.swayam.products.drutochithhi.addrstore.config.RepoConfig
  (:require
    [omniconf.core :as cfg]
    [mount.core :refer [defstate] :as mount]
    )
  (:import (com.zaxxer.hikari HikariDataSource)
   )
)

(defn create-connection-pool
  []
  (let [hikari-ds (doto (HikariDataSource.)
                    (.setDriverClassName (cfg/get :database-driver))
                    (.setJdbcUrl (cfg/get :database-url))
                    (.setUsername (cfg/get :database-user))
                    (.setPassword (cfg/get :database-password))
                    )]
    {:datasource hikari-ds}))


(defstate address-store-db  
  :start (create-connection-pool)  
  :stop (.close (address-store-db :datasource))
  )
