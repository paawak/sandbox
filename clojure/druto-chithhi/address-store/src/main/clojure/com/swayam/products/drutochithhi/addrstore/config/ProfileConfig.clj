(ns com.swayam.products.drutochithhi.addrstore.config.ProfileConfig
  (:require [omniconf.core :as cfg]
            [clojure.java.io :as io]
            [clojure.tools.logging :as log]
   )
  )

(def CONFIG_FILE_LOCATION_SYSTEM_PROPERTY "config.file"
  )

(defn load-configs
  []
  (log/info "Looking for the System variable " CONFIG_FILE_LOCATION_SYSTEM_PROPERTY " for the config file location")
  (let [ 
        configFileLocation (System/getProperty CONFIG_FILE_LOCATION_SYSTEM_PROPERTY)
        configFileUri (io/resource configFileLocation)]
         (log/info "config file location: "  configFileLocation)
         (log/info "config file URI: "  configFileUri)
         (cfg/populate-from-file configFileUri)
    )
 )
