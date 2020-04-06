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
  (let [num (count (char-array msg))]
  (clojure.string/replace msg #"\w"  (fn [letter] (
    let [charArray (char-array letter) 
         firstChar (first charArray) 
         intValue (int firstChar)
         seed (+ intValue num)
         ] (str "#" (int (Math/pow seed 2)))) ))
  )
  )

(def gemstone-db {
    :ruby {
      :name "Ruby"
      :stock 120
      :sales [1990 3644 6376 4918 7882 6747 7495 8573 5097 1712]
      :properties {
        :dispersion 0.018
        :hardness 9.0
        :refractive-index [1.77 1.78]
        :color "Red"
      }
    }
   :diamond {
      :name "Diamond"
      :stock 10
      :sales [8295 329 5960 6118 4189 3436 9833 8870 9700 7182 7061 1579]
      :properties {
        :dispersion 0.044
        :hardness 10
        :refractive-index [2.417 2.419]
        :color "Typically yellow, brown or gray to colorless"
      }
    }
    :moissanite {
      :name "Moissanite"
      :stock 45
      :sales [7761 3220]
      :properties {
        :dispersion 0.104
        :hardness 9.5
        :refractive-index [2.65 2.69]
        :color "Colorless, green, yellow"
      }
    }
  }
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
