(ns soundcloud-clj.utils
  (:refer-clojure)
  (:use [clj-http.client :only [success?]])
  (:require [clojure.string]
            [clojure.data.json     :as json]
            [soundcloud-clj.config :as config]))

;;
;; Transformations
;;

(defrecord SoundCloudEndpoint
    [uri client-id client-secret])

(def ^{:dynamic true} *endpoint*)

(defn discard-nils
  [map]
  (apply dissoc map (for [[k v] map :when (nil? v)] k)))

;;
;; Request transformations
;;
(defn paginate
  [options]
  (if-let [page (:page options)]
    (merge (dissoc options :page) {:limit config/per-page :offset (* (- page 1) config/per-page) })
    (if (= false (:paginate options))
      options
      (paginate (merge options {:page 1})))))

(defn add-client-id
  [options]
  (merge options { :client_id (:client-id (config/endpoint)) }))

(defn add-oauth-token
  [options oauth-token]
  (merge options { :oauth_token oauth-token }))

(defn transform-query-params
  ([query-params]
     (-> query-params
         discard-nils
         paginate
         add-client-id))
  ([query-params oauth-token]
     (-> query-params
         discard-nils
         paginate
         (add-oauth-token oauth-token))))

;;
;; Map/vector/string/keyword transformations
;;

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
;; Response operations
;;

(defn transform-response
  [response]
  (if (success? response)
    (-> (:body response)
        json/read-json
        dasherize)
    (merge (json/read-json (:body response)) (select-keys response [:status]))))
