(ns com.swayam.products.drutochithhi.clientaccount.repo.CountryDao
  (:require [clojure.java.jdbc :as jdbc]
            [com.swayam.products.drutochithhi.clientaccount.repo.RepoConfig :as config]
  )
  (:import (com.swayam.products.drutochithhi.clientaccount.model.Models Country))
 )


(defn add-new-country
  [country]
  ;; remove the id field before insert
  (jdbc/insert! config/datasource :country (dissoc country :id))
  )


