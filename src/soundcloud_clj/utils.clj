(ns soundcloud-clj.utils
  (:refer-clojure)
  (:require [clojure.string]
            [soundcloud-clj.config   :as config]))

;;
;; Transformations
;;

(defrecord SoundCloudEndpoint
    [uri client-id client-secret])

(def ^{:dynamic true} *endpoint*)

(defn discard-nils
  [map]
  (apply dissoc map (for [[k v] map :when (nil? v)] k)))

(defn paginate
  [options]
  (if-let [page (:page options)]
    (merge (dissoc options :page) {:limit config/per-page :offset (* (- page 1) config/per-page) })
    (paginate (merge options {:page 1}))))

(defn add-client-id
  [options]
  (merge options { :client_id (:client-id (config/endpoint)) }))

(defn transform-query-params
  [query-params]
  (-> query-params
      discard-nils
      paginate
      add-client-id))

(defprotocol Transform
  (dasherize [object] "")
  (underscore [object] ""))

(extend-type java.lang.String
  Transform
  (dasherize [str]
    (clojure.string/replace str #"_" "-"))
  (underscore [str]
     (clojure.string/replace str #"-" "_")))

(extend-type clojure.lang.Keyword
  Transform
  (dasherize [kwd]
    (keyword (dasherize (name kwd))))
  (underscore [kwd]
    (keyword (underscore (name kwd)))))

(extend-type nil
  Transform
  (dasherize [_]
    nil)
  (underscore [_]
    nil))

(extend-type clojure.lang.IPersistentMap
  Transform
  (dasherize [m]
    (zipmap
     (map #(keyword (dasherize %)) (keys m))
     (vals m)))
  (underscore [m]
    (zipmap
     (map #(keyword (underscore %)) (keys m))
     (vals m))))


(extend-type clojure.lang.IPersistentVector
  Transform
  (dasherize [v]
    (map #(dasherize %) v))
  (underscore [v]
    (map #(underscore %) v)))

;;
;; HTTP Statuses
;;

(defn success?
  [response]
  (<= 200 (:status response) 299))

(defn missing?
  [response]
  (= (:status response) 404))

(defn conflict?
  [response]
  (= (:status response) 409))


(defn redirect?
  [response]
  (<= 300 (:status response) 399))

(defn client-error?
  [response]
  (<= 400 (:status response) 499))

(defn server-error?
  [response]
  (<= 500 (:status response) 599))



