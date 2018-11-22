(ns learn-clojure.core
  (:gen-class))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn -main
  "This is the description about waht this method does"
  []
  (foo "Main")
  )
