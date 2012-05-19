(ns soundcloud-clj.rest
  (:refer-clojure :exclude [get])
  (:use     [soundcloud-clj.utils])
  (:require [clj-http.client   :as http]
            [clojure.data.json :as json]))

(def ^:const throw-exceptions false)

(defn POST
  [^String uri &{ :keys [body] :as options }]
  (io! (json/read-json
        (:body (http/post uri (merge options { :accept :json :body (json/json-str body) }))))))

(defn PUT
  [^String uri &{ :keys [body] :as options}]
  (io! (json/read-json
        (:body (http/put uri (merge options { :accept :json :body (json/json-str body)  :throw-exceptions throw-exceptions }))))))

(defn GET
  ([^String uri]
     (io! (json/read-json
           (:body (http/get uri { :accept :json :throw-exceptions throw-exceptions })))))
  ([^String uri &{ :as options }]
     (let [response (io! (http/get uri (merge options {:accept :json :throw-exceptions throw-exceptions})))]
       (if (success? response)
         (dasherize (json/read-json (:body response)))
         (json/read-json (:body response))))))

(defn HEAD
  [^String uri]
  (io! (http/head uri { :accept :json :throw-exceptions throw-exceptions })))

(defn DELETE
  ([^String uri]
     (io! (json/read-json
           (:body (http/delete uri { :accept :json :throw-exceptions throw-exceptions })))))
  ([^String uri &{ :keys [body] :as options }]
     (io! (json/read-json
           (:body (http/delete uri (merge options { :accept :json :body (json/json-str body) :throw-exceptions throw-exceptions })))))))