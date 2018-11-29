(ns recursion.binarytree
  (:gen-class)
  )

(defrecord Node[value left right])

(def root (atom (->Node nil nil nil)))

(defn addNode [n]
    (println "Trying to add " n " into the Tree")
    (if (nil? (:value @root))
      (do
            (println "Assigning the newNode to root")
            (swap! root assoc :value n)
        )
      (do
        (println "Root exists")
        (loop [currentNode root]
          (if (= n (:value @currentNode))
            (println "Ignore silently, as duplicates are not allowed")
            (do
              (cond (< n (:value @currentNode))
                (do
                  (println "Left")
                  (if (nil? (:left @currentNode))
                    (swap! currentNode assoc :left (atom (->Node n nil nil)))
                    (recur (:left @currentNode))
                    )
                )
                (> n (:value @currentNode))
                (do
                  (println "Right")
                  (if (nil? (:right @currentNode))
                    (swap! currentNode assoc :right (atom (->Node n nil nil)))
                    (recur (:right @currentNode))
                    )
                )
                )
              )
            )
          
          )
        )
      )
    (println "--------------Added a new Node")
  )

(defn -main
  []
    (addNode 4)
    (addNode 3)
    (addNode 6)
    (addNode 4)
    (println "The root of the tree is: " root)
  )

