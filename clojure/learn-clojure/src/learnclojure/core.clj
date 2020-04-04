(ns learnclojure.core
(:gen-class)
)

;(co2-estimate 2009)
(defn co2-estimate
  "Estimates CO2"
  [year]
  (let [startYear 2006 initialPPM 382 factor 2]
           (println "calculating co2 for the year " year)
           (+ initialPPM (* factor (- year startYear)))
    )
  )

;(calmness 1 "hello")
;(calmness 5 "hello")
;(calmness 10 "hello")
(defn calmness
  "degrees of calmness"
  [calm text]
  (println "Clojure Meditate v2.0")
  (cond (< calm 5) (str (clojure.string/upper-case text) ", I TELL YA!") 
  (<= 5 calm 9)  (clojure.string/capitalize text)
  (= calm 10)  (clojure.string/reverse text)
  )
  )

(defn encode
  "encodes with the best algo"
  [msg]
  (println msg)
  (clojure.string/replace msg #"\w"  (fn [letter] (
    let [charArray (char-array letter) 
         firstChar (first charArray) 
         intValue (int firstChar)
         totalLength (count (char-array msg))
         seed (+ intValue totalLength)
         ] (str (Math/pow seed 2))) ))
  )

(defn findFact
  [d]
   (
     long (* d 10)
    )
  )



(defn -main
  []
  (
    (def y 20)
    (def x (findFact y))
    ;;(let [y x] y)
    (println (str "Factorial of " y " is: " x))
    )

  )
