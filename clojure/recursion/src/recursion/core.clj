(ns recursion.core
  (:gen-class)
  )

(defn findFact
  "I don't do a whole lot."
  [x]
  (println "Hello, World! " x))

(defn -main
  []
  (
    (findFact 20)
    )

  )