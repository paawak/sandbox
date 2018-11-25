(ns recursion.binarytree
  (:gen-class)
  )

(defrecord Node[value left right])


(defn add [n]
  (
    (println "Trying to add " n " into the Tree")
    (def newNode (->Node n nil nil))
    )
  )

(defn -main
  []
  (
    (add 4)
    (println "Hello!")
    )

  )

