;; Execute these forms by copy-and-pasting them, one at a time, into
;; the Clojure REPL, or by using your editor's integrated REPL.

;; Load the Datomic API library.
(require '[datomic.api :as d])

;; Define the connection URI for a local in-memory database.
(def uri "datomic:mem://hello")

;; Create the new database.
(d/create-database uri)

;; Connect to it.
(def conn (d/connect uri))

;; Define, but do not execute, a transaction.
;; Transactions are data.
(def tx
  [[:db/add
    (d/tempid :db.part/user)  ; entity
    :db/doc                   ; attribute
    "Hello world"]])          ; value

;; Execute the transaction, storing the result in tx-result.
;; Transactions are executed on a connection and return a promise.
(def tx-result (d/transact conn tx))

;; Look at the contents of tx-result. It contains the database values,
;; before and after the transaction, and the datoms that were created
;; by that transaction.
(keys @tx-result)

(:tx-data @tx-result)

;; Get the current value of the database and save it as 'dbval'.
(def dbval (d/db conn))

;; Define, but do not execute, a query.
;; Queries are data.
(def query
  '[:find ?e
    :where [?e :db/doc "Hello world"]])

;; Execute the query with the database value as input.
(def q-result
  (d/q query dbval))

;; Look at the result of the query. The results of this query are
;; entity IDs.
(prn q-result)

;; Get an entity map of the first query result.
(def ent (d/entity dbval (ffirst q-result)))

;; Entity maps are lazily loaded: values of attributes are not
;; retrieved until we ask for them. If we print the entity right now,
;; it does not appear to have any attributes.
(prn ent)

;; We can see what attributes an entity has by calling `keys`:
(keys ent)

;; And retrieve the value of any attribute by asking for it:
(:db/doc ent)

;; The schema itself is data: we can use an attribute keyword in place
;; of an entity ID to get the entity representing that attribute.
(def doc-ent (d/entity dbval :db/doc))

;; We can force the entity map to load all of its attributes, normally
;; only needed for debugging.
(d/touch doc-ent)
