(ns soundcloud-clj.me
  (:refer-clojure :exclude [get])
  (:use     [soundcloud-clj.utils]
            [clj-http.client :only [success?]])

  (:require [soundcloud-clj.rest :as rest]
            [soundcloud-clj.urls :as urls]
            [soundcloud-clj.config :as config]))

(defn me
  [oauth-token]
  (transform-response
   (rest/GET (urls/me) :query-params (transform-query-params {} oauth-token))))

(defn my-tracks
  [oauth-token]
  (transform-response
   (rest/GET (urls/my-tracks) :query-params (transform-query-params {} oauth-token))))

(defn following?
  [user-id oauth-token]
  (success?
   (rest/GET (urls/my-followings user-id) :query-params (transform-query-params {} oauth-token))))

(defn my-followings
  [oauth-token]
  (transform-response
   (rest/GET (urls/my-followings) :query-params (transform-query-params {} oauth-token))))

(defn my-following
  [user-id oauth-token]
  (transform-response
   (rest/GET (urls/my-followings user-id) :query-params (transform-query-params {} oauth-token))))

(defn my-followings
  [oauth-token]
  (transform-response
   (rest/GET (urls/my-followings) :query-params (transform-query-params {} oauth-token))))

(defn follow-user
  [user-id oauth-token]
  (transform-response
   (rest/PUT (urls/my-followings user-id) :query-params (transform-query-params {} oauth-token))))

(defn unfollow-user
  [user-id oauth-token]
  (transform-response
   (rest/DELETE (urls/my-followings user-id) :query-params (transform-query-params {} oauth-token))))
