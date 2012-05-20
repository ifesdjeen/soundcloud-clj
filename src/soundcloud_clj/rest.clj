(ns soundcloud-clj.rest
  (:refer-clojure :exclude [get])
  (:use     [soundcloud-clj.utils])
  (:require [clj-http.client   :as http]
            [clojure.data.json :as json]))

(def ^:const throw-exceptions false)

(defn POST
  [^String uri &{ :keys [body] :as options }]
  (io!
   (http/post uri (merge options { :accept :json :body (json/json-str body) }))))

(defn PUT
  [^String uri &{ :keys [body] :as options}]
  (io!
   (http/put uri (merge options { :accept :json :body (json/json-str body)  :throw-exceptions throw-exceptions }))))

(defn GET
  ([^String uri]
     (io!
      (http/get uri { :accept :json :throw-exceptions throw-exceptions })))
  ([^String uri &{ :as options }]
     (io! (http/get uri (merge options {:accept :json :throw-exceptions throw-exceptions})))))

(defn HEAD
  [^String uri]
  (io!
   (http/head uri { :accept :json :throw-exceptions throw-exceptions })))

(defn DELETE
  ([^String uri]
     (io!
      (http/delete uri { :accept :json :throw-exceptions throw-exceptions })))
  ([^String uri &{ :keys [body] :as options }]
     (io!
      (http/delete uri (merge options { :accept :json :body (json/json-str body) :throw-exceptions throw-exceptions })))))