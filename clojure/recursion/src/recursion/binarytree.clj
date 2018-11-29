(ns recursion.binarytree
  (:gen-class)
  )

(defrecord Node[value left right])

(def root nil)

(defn addNode [n]
    (println "Trying to add " n " into the Tree")
    (if (nil? root)
      (do
            (println "Assigning the newNode to root")
            (def root (map->Node {:value n}))
        )
      (do
        (println "Root exists")
        (loop [currentNode root]
          (cond (< n (:value currentNode))
                (println "Left")
                (> n (:value currentNode))
                (println "Right")
                :else (println "Ignore silently, as duplicates are not allowed")
            )
          )
        )
      )
    (println "Added a new Node")
  )

(defn -main
  []
    (addNode 4)
    (addNode 3)
    (addNode 6)
    (addNode 4)
    (println "The root of the tree is: " root)
  )

