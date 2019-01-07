(ns com.swayam.products.drutochithhi.addrstore.config.RepoConfig
  (:require
    [omniconf.core :as cfg]
    [mount.core :refer [defstate] :as mount]
    )
  (:import (com.zaxxer.hikari HikariDataSource)
   )
)

(defn get-address-store-db-spec
  []
  {
     :driver (cfg/get :database-driver)
	   :url (cfg/get :database-url)
	   :user (cfg/get :database-user)
	   :password (cfg/get :database-password)
  }
  )

(defn create-connection-pool
  []
  (let [
        db-spec (get-address-store-db-spec)
        hikari-ds (doto (HikariDataSource.)
                    (.setDriverClassName (:driver db-spec))
                    (.setJdbcUrl (:url db-spec))
                    (.setUsername (:user db-spec))
                    (.setPassword (:password db-spec))
                    )]
    {:datasource hikari-ds}))


(defstate address-store-db  
  :start (create-connection-pool)  
  :stop (.close (address-store-db :datasource))
  )
