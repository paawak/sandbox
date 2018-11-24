(ns learnclojure.core)


(defn findFact
  [d]
   (
     long (* d 10)
    )
  )



(defn -main
  [& args]
  (
    (def y 20)
    (def x (findFact y))
    ;;(let [y x] y)
    (println (str "Factorial of " y " is: " x))
    )

  )
