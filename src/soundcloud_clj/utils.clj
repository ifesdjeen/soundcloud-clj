(ns soundcloud-clj.utils
  (:refer-clojure)
  (:require clojure.string))

;;
;; Transformations
;;

(defrecord SoundCloudEndpoint
    [uri client-id client-secret])

(def ^{:dynamic true} *endpoint*)

(defn discard-nils
  [map]
  (apply dissoc map (for [[k v] map :when (nil? v)] k)))

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



