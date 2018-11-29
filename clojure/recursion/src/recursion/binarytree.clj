(ns recursion.binarytree
  (:gen-class)
  )

(defrecord Node[value left right])

(def root (atom (->Node nil nil nil)))

(defn addNode [n]
    (if (nil? (:value @root))
      (swap! root assoc :value n)
        (loop [currentNode root]
          (if (= n (:value @currentNode))
            (println "Ignore silently, as duplicates are not allowed")
              (cond (< n (:value @currentNode))
                  (if (nil? (:left @currentNode))
                    (swap! currentNode assoc :left (atom (->Node n nil nil)))
                    (recur (:left @currentNode))
                    )
                (> n (:value @currentNode))
                  (if (nil? (:right @currentNode))
                    (swap! currentNode assoc :right (atom (->Node n nil nil)))
                    (recur (:right @currentNode))
                    )
                )
            )
          
          )
      )
  )

(defn printElements
  [node]
    (if (:left @node)
      (printElements (:left @node))
      )
    (print (:value @node) "  ")
    (if (:right @node)
      (printElements (:right @node))
      )    
  )

(defn -main
  []

    (doseq [x [4 3 6 14 36 21 100 20 19 70 81 7 97 200]]
      (addNode x)
      )
    
    (println "-------------- Printing elements")
    (printElements root)
  )

