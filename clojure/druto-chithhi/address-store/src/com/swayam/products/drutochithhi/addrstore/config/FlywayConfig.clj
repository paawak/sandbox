(ns com.swayam.products.drutochithhi.addrstore.config.FlywayConfig
  (:require
  [omniconf.core :as cfg]
  [com.swayam.products.drutochithhi.addrstore.config.RepoConfig :as repoConfig]
  )
  (:import (org.flywaydb.core Flyway)
   )
  )

(defn run-flyway
  []
  (println "Running flyway...")
  (let [
        db-spec (repoConfig/get-address-store-db-spec)
        flywayFluentConfiguration
        (doto (Flyway/configure)
            (.locations (into-array [(cfg/get :flyway-sql-directory)]))
            (.dataSource (:url db-spec) (:user db-spec) (:password db-spec) )
         )
        flyway
        (.load flywayFluentConfiguration)
        ]
    (.migrate flyway)
    )
  )