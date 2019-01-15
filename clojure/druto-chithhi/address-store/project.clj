(defproject address-store "0.0.1-SNAPSHOT"
  :min-lein-version "2.0.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]
  :resource-paths ["src/main/resources"]

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [io.pedestal/pedestal.service "0.5.4"]
                 [io.pedestal/pedestal.tomcat "0.5.4"]
                 [org.clojure/java.jdbc "0.7.8"]
                 [org.postgresql/postgresql "42.2.5"]
                 [com.zaxxer/HikariCP "3.1.0"]
                 [org.clojure/data.json "0.2.6"]
                 [com.grammarly/omniconf "0.3.2"]
                 [mount "0.1.15"]
                 [org.flywaydb/flyway-core "5.2.4"]

                 [org.clojure/tools.logging "0.4.1"]
                 [ch.qos.logback/logback-classic "1.2.3" :exclusions [org.slf4j/slf4j-api]]
                 [org.slf4j/jul-to-slf4j "1.7.25"]
                 [org.slf4j/jcl-over-slf4j "1.7.25"]
                 [org.slf4j/log4j-over-slf4j "1.7.25"]
                 ]

  :profiles {
             :dev {:jvm-opts ["-Dconfig.file=dev/address_store_config.edn"]
;                   :aliases {"run-dev" ["trampoline" "run" "-m" "com.swayam.products.drutochithhi.addrstore.server/run-dev"]}
;                   :dependencies [
;                                  [io.pedestal/pedestal.service-tools "0.5.4"]
;                                  [clj-http "2.0.0"]
;                                  ]
;                   :plugins [[cider/cider-nrepl "0.20.0"]]
                   }
             :uberjar {:aot [com.swayam.products.drutochithhi.addrstore.server]}
             :test {
                    :jvm-opts ["-Dconfig.file=test/address_store_test_config.edn"]
                    :resource-paths ["src/test/resources"]
                    :dependencies [[com.h2database/h2 "1.4.197"]]
                    }
             }
  :main ^{:skip-aot true} com.swayam.products.drutochithhi.addrstore.server)

