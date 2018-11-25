(ns recursion.binarytree
  (:gen-class)
  )

(defrecord Node[value left right])

(def root nil)

(defn addNode [n]
    (println "Trying to add " n " into the Tree")
    (def newNode (->Node n nil nil))
    (println "11111111")
    (if (nil? root)
      (do (
            println "5555555"
            (def root newNode)
            println "666666666 " root
            )
        )
      )
    (println "22222222 " root)
    (println "The root of the tree is: " root)
  )

(defn -main
  []
    (println "3333333")
    (addNode 4)
    (println "444444444")
    (println "The root of the tree is: " root)
  )

