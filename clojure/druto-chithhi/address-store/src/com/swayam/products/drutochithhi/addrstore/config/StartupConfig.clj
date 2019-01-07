(ns com.swayam.products.drutochithhi.addrstore.config.StartupConfig
  (:require 
            [mount.core :as mount]
            [com.swayam.products.drutochithhi.addrstore.config.ProfileConfig :as conf]
            [com.swayam.products.drutochithhi.addrstore.config.FlywayConfig :as flyway]
            )
  )

(defn load-config-file
  []
  (println "Loading config...")
  (conf/load-configs)
  )

(defn start-mount
  []
  (println "Starting mount...")
  (mount/start)
  (.addShutdownHook
   (Runtime/getRuntime)
   (Thread. mount/stop))
  )

(defn init-on-startup
  []
  (load-config-file)
  (start-mount)
  (flyway/run-flyway)
  )

