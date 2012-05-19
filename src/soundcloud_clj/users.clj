(ns soundcloud-clj.users
  (:refer-clojure :exclude [get])
  (:require [soundcloud-clj.rest :as rest]
            [soundcloud-clj.urls :as urls]
            [soundcloud-clj.config :as config]))

(defn get-users
  []
  (rest/GET (urls/users) :query-params { :client_id (:client-id (config/endpoint)) }))

(defn get-user
  [user-id]
  (rest/GET (urls/users user-id) :query-params { :client_id (:client-id (config/endpoint)) }))