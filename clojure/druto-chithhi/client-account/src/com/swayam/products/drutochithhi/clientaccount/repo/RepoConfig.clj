(ns com.swayam.products.drutochithhi.clientaccount.repo.RepoConfig
  (:require [clojure.java.jdbc :as jdbc]
  )
)

(def db-spec
  {:dbtype "postgres"
   :dbname "druto-chithhi-client-account"
   :user "postgres"
   :password "postgres"})

