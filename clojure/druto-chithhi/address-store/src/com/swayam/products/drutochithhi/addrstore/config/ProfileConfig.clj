(ns com.swayam.products.drutochithhi.addrstore.config.Configs
  (:require [omniconf.core :as cfg]
            [clojure.java.io :as io]
   )
  )

(def CONFIG_FILE_LOCATION_SYSTEM_PROPERTY "config.file"
  )

(defn load-configs
  []
  (println "Looking for the System variable " CONFIG_FILE_LOCATION_SYSTEM_PROPERTY " for the config file location")
  (let [ 
        configFileLocation (System/getProperty CONFIG_FILE_LOCATION_SYSTEM_PROPERTY)
        configFileUri (io/resource configFileLocation)]
         (println "config file location: "  configFileLocation)
         (println "config file URI: "  configFileUri)
         (cfg/populate-from-file configFileUri)
    )
 )
