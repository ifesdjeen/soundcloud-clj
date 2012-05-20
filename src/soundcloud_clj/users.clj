(ns soundcloud-clj.users
  (:refer-clojure :exclude [get])
  (:use     [soundcloud-clj.utils])
  (:require [soundcloud-clj.rest :as rest]
            [soundcloud-clj.urls :as urls]
            [soundcloud-clj.config :as config]))

(defn get-users
  [&{:keys [q page] :as options}]
  (transform-response
   (rest/GET (urls/users) :query-params (transform-query-params options))))

(defn get-user
  [user-id]
  (transform-response
   (rest/GET (urls/users user-id) :query-params (transform-query-params {}))))

(defn get-user-tracks
  [user-id &{:keys [q page] :as options}]
  (transform-response
   (rest/GET (urls/user-tracks user-id) :query-params (transform-query-params options))))

(defn get-user-followings
  [user-id &{:keys [q page] :as options}]
  (transform-response
   (rest/GET (urls/user-followings user-id) :query-params (transform-query-params options))))

(defn get-user-followers
  [user-id &{:keys [q page] :as options}]
  (transform-response
   (rest/GET (urls/user-followers user-id) :query-params (transform-query-params options))))

(defn get-user-playlists
  [user-id &{:keys [q page] :as options}]
  (transform-response
   (rest/GET (urls/user-playlists user-id) :query-params (transform-query-params options))))

