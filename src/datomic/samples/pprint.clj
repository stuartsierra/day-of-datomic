(ns datomic.samples.pprint
  "Extend Clojure's pretty-printer to Datomic entity maps and
  temporary entity IDs. This relies on internal implementation details
  of clojure.pprint and will break if they change."
  (:require clojure.pprint))

(in-ns 'clojure.pprint)

(use-method
 simple-dispatch
 datomic.query.EntityMap
 (fn [entity]
   (pprint-map (cons [:db/id (:db/id entity)] (seq entity)))))

(use-method
 clojure.pprint/simple-dispatch
 datomic.db.DbId
 (fn [{:keys [part idx] :as id}]
   (if (< idx -1000000)
     (print (str "#db/id[" part "]"))
     (pr id))))
