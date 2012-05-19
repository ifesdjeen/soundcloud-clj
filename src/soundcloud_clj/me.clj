(ns soundcloud-clj.me
  (:refer-clojure :exclude [get])
  (:require [soundcloud-clj.rest :as rest]
            [soundcloud-clj.urls :as urls]
            [soundcloud-clj.utils :as utils]
            [soundcloud-clj.config :as config]))

(defn me
  [oauth-token]
  (rest/GET (urls/me) :query-params (utils/transform-query-params {} oauth-token)))

(defn my-tracks
  [oauth-token]
  (rest/GET (urls/my-tracks) :query-params (utils/transform-query-params {} oauth-token)))

(defn my-followings
  [oauth-token]
  (rest/GET (urls/my-followings) :query-params (utils/transform-query-params {} oauth-token)))

(defn follow-user
  [user-id oauth-token]
  (rest/PUT (urls/my-followings user-id) :query-params (utils/transform-query-params {} oauth-token)))

(defn unfollow-user
  [user-id oauth-token]
  (rest/DELETE (urls/my-followings user-id) :query-params (utils/transform-query-params {} oauth-token)))
