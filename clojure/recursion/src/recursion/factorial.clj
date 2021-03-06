(ns recursion.factorial
  (:gen-class)
  )

(defn findFact
  [n]
    (loop [currentNum n
           result 1]
       (if (= currentNum 1)
            result
          (recur (dec currentNum) (* result currentNum))
          )
       )
  )


(defn -main
  []
    (println "The factorial is: " (findFact 5))
  )