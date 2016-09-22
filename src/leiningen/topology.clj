(ns leiningen.topology
  (:require [leiningen.core.eval :as lein]))

(defn topology [project & args]
  (lein/eval-in-project (update-in project [:dependencies] conj
                                   '[lein-topology "0.1.1"]
                                   '[org.clojure/clojure "1.8.0"])
                        `(topology.core/print-weighted-edges
                           (topology.core/all-ns->fn-edges
                            ~@(flatten (conj (:source-paths project) (:test-paths project)))))
                        `(require 'topology.core)))
