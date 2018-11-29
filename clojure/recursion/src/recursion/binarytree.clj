(ns recursion.binarytree
  (:gen-class)
  )

(defrecord Node[value left right])

(def root (atom (->Node nil nil nil)))

(defn addNode [n currentNode]
    (if (nil? (:value @currentNode))
      (do
            (swap! currentNode assoc :value n)
        )
      (do
          (if (= n (:value @currentNode))
            (println "Ignore silently, as duplicates are not allowed")
            (do
              (cond (> n (:value @currentNode))
                  (if (nil? (:left @currentNode))
                    (swap! currentNode assoc :left (atom (->Node n nil nil)))
                    (addNode n (:left @currentNode))
                    )
                (< n (:value @currentNode))
                  (if (nil? (:right @currentNode))
                    (swap! currentNode assoc :right (atom (->Node n nil nil)))
                    (addNode n (:right @currentNode))
                    )
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
    (addNode 4 root)
    (addNode 3 root)
    (addNode 6 root)
    (addNode 14 root)
    (addNode 36 root)
    (addNode 100 root)
    (addNode 20 root)
    (addNode 19 root)
    (addNode 70 root)
    (addNode 81 root)
    (addNode 97 root)
    (addNode 200 root)
    (println "Printing nodes of the tree in pre-order")
    (printElements root)
  )

