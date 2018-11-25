(ns recursion.binarytree
  (:gen-class)
  )

(defrecord Node[value left right])

(def root nil)

(defn addNode [n]
    (println "Trying to add " n " into the Tree")
    (def newNode (->Node n nil nil))
    (if (nil? root)
      (do
            (println "Assigning the newNode to root")
            (def root newNode)
        )
      )
    (println "Added a new Node")
  )

(defn -main
  []
    (addNode 4)
    (println "The root of the tree is: " root)
  )

