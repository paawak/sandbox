(defproject address-store "0.0.1-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [io.pedestal/pedestal.service "0.5.4"]
                 [io.pedestal/pedestal.tomcat "0.5.4"]
                 [org.clojure/java.jdbc "0.7.8"]
                 [org.postgresql/postgresql "42.2.5"]
                 [com.zaxxer/HikariCP "3.1.0"]
                 [org.clojure/data.json "0.2.6"]
                 [com.grammarly/omniconf "0.3.2"]
                 [mount "0.1.15"]

                 [ch.qos.logback/logback-classic "1.2.3" :exclusions [org.slf4j/slf4j-api]]
                 [org.slf4j/jul-to-slf4j "1.7.25"]
                 [org.slf4j/jcl-over-slf4j "1.7.25"]
                 [org.slf4j/log4j-over-slf4j "1.7.25"]]
  :min-lein-version "2.0.0"
  :resource-paths ["config"]
  :profiles {
             :uberjar {:aot [com.swayam.products.drutochithhi.addrstore.server]}
             }
  :main ^{:skip-aot true} com.swayam.products.drutochithhi.addrstore.server)

