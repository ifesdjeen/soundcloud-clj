(ns soundcloud-clj.users
  (:refer-clojure :exclude [get])
  (:require [soundcloud-clj.rest :as rest]
            [soundcloud-clj.urls :as urls]
            [soundcloud-clj.utils :as utils]
            [soundcloud-clj.config :as config]))

(defn get-users
  [&{:keys [q page] :as options}]
  (rest/GET (urls/users) :query-params (utils/transform-query-params options)))

(defn get-user
  [user-id]
  (rest/GET (urls/users user-id) :query-params (utils/transform-query-params {})))

(defn get-user-tracks
  [user-id &{:keys [q page] :as options}]
  (rest/GET (urls/user-tracks user-id) :query-params (utils/transform-query-params options)))

(defn get-user-followings
  [user-id &{:keys [q page] :as options}]
  (rest/GET (urls/user-followings user-id) :query-params (utils/transform-query-params options)))

(defn get-user-followers
  [user-id &{:keys [q page] :as options}]
  (rest/GET (urls/user-followers user-id) :query-params (utils/transform-query-params options)))

(defn get-user-playlists
  [user-id &{:keys [q page] :as options}]
  (rest/GET (urls/user-playlists user-id) :query-params (utils/transform-query-params options)))

