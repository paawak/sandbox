(ns learnclojure.core
  (:require [clojure.java.io :as io] )
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


(def game-users
  [{:id 9342
    :username "speedy"
    :current-points 45
    :remaining-lives 2
    :experience-level 5
    :status :active}
   {:id 9854
    :username "stealthy"
    :current-points 1201
    :remaining-lives 1
    :experience-level 8
    :status :speed-boost}
   {:id 3014
    :username "sneaky"
    :current-points 725
    :remaining-lives 7
    :experience-level 3
    :status :active}
   {:id 2051
    :username "forgetful"
    :current-points 89
    :remaining-lives 4
    :experience-level 5
    :status :imprisoned}
   {:id 1032
    :username "wandering"
    :current-points 2043
    :remaining-lives 12
    :experience-level 7
    :status :speed-boost}
   {:id 7213
    :username "slowish"
    :current-points 143
    :remaining-lives 0
    :experience-level 1
    :status :speed-boost}
   {:id 5633
    :username "smarter"
    :current-points 99
    :remaining-lives 4
    :experience-level 4
    :status :terminated}
   {:id 3954
    :username "crafty"
    :current-points 21
    :remaining-lives 2
    :experience-level 8
    :status :active}
   {:id 7213
    :username "smarty"
    :current-points 290
    :remaining-lives 5
    :experience-level 12
    :status :terminated}
   {:id 3002
    :username "clever"
    :current-points 681
    :remaining-lives 1
    :experience-level 8
    :status :active}])

;(game-stats game-users)
(defn game-stats
  [players]
  (map #(:current-points %) players
    ) 
  )

;(tennis-stats "match_scores_1877-1967_unindexed_csv.csv")
(defn tennis-stats
  [statsFileCsv]
  (let [dataFile (io/resource statsFileCsv)
        rawLines (slurp dataFile)
        ]
    (println (count rawLines))
    ) 
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
