;; Execute forms one-at-a-time in the Clojure REPL.

(require
 '[datomic.api :as d]
 '[datomic.samples.news :as news])

(def uri "datomic:mem://news")

(def conn (news/setup-sample-db-1 uri))

(def db (d/db conn))

;; Find all users.
(d/q '[:find ?e
       :where [?e :user/email]]
     db)

;; Find user by email.
(d/q '[:find ?e
       :in $ ?email
       :where [?e :user/email ?email]]
     db
     "editor@example.com")

;; Find user's comments.
(d/q '[:find ?comment
       :in $ ?email
       :where [?user :user/email ?email]
       [?comment :comment/author ?user]]
     db
     "editor@example.com")

;; How many comments has user made?
(d/q '[:find (count ?comment)
       :in $ ?email
       :where [?user :user/email ?email]
       [?comment :comment/author ?user]]
     db
     "editor@example.com")

;; What other attributes do things with comments have?
(d/q '[:find ?attr-name
       :where
       [?ref :comments]
       [?ref ?attr]
       [?attr :db/ident ?attr-name]]
     db)

;; Get the entity ID of a user.
(def editor-id (->> (d/q '[:find ?e
                           :in $ ?email
                           :where [?e :user/email ?email]]
                         db
                         "editor@example.com")
                    ffirst))

;; Get the user as an entity map.
(def editor (d/entity db editor-id))

(:user/firstName editor)

(d/touch editor)

;; Find comments authored by this user.
(-> editor :comment/_author)

;; Flatten out the comments.
(->> editor :comment/_author (mapcat :comments))

;; Find the transaction in which user's firstName was set.
(def txid (->> (d/q '[:find ?tx
                      :in $ ?e
                      :where [?e :user/firstName _ ?tx]]
                    db
                    editor-id)
                 ffirst))

;; Get the 't' value of that transaction.
(d/tx->t txid)

;; Get the real-world time that transaction occurred.
(-> (d/entity (d/db conn) txid)
    :db/txInstant)

;; Compare with the user's name in the past.
(def older-db (d/as-of db (dec txid)))
(:user/firstName (d/entity older-db editor-id))

;; Get a historical view of the database containing all transactions.
(def hist (d/history db))

;; Look at changes in the user's name over time.
(->> (d/q '[:find ?tx ?v ?op
            :in $ ?e ?attr
            :where [?e ?attr ?v ?tx ?op]]
          hist
          editor-id
          :user/firstName)
     (sort-by first))

;; Query over literal tuple data.
(d/q '[:find ?e
       :where [?e :user/email]]
     [[1 :user/email "jdoe@example.com"]
      [1 :user/firstName "John"]
      [2 :user/email "jane@example.com"]])
