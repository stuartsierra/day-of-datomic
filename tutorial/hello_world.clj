(require '[datomic.api :as d])
(def uri "datomic:mem://hello")
(d/create-database uri)
(def conn (d/connect uri))

;; transaction input is data
(def tx
  [[:db/add
    (d/tempid :db.part/user)  ; entity
    :db/doc                   ; attribute
    "Hello world"]])          ; value

;; transaction result is data
(def tx-result (d/transact conn tx))

tx-result

(def dbval (d/db conn))

;; query input is data
(def q-result
  (d/q '[:find ?e
         :where [?e :db/doc "Hello world"]]
       dbval))

;; query result is data
q-result

;; entity is a navigable view over data
(def ent (d/entity dbval (ffirst q-result)))

;; entities are lazy until we ask
(:db/doc ent)

;; schema itself is data
(def doc-ent (d/entity dbval :db/doc))

;; force loading of all attributes
(d/touch doc-ent)
