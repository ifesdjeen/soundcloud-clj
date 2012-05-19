(ns soundcloud-clj.me
  (:refer-clojure :exclude [get])
  (:require [soundcloud-clj.rest :as rest]
            [soundcloud-clj.urls :as urls]
            [soundcloud-clj.utils :as utils]
            [soundcloud-clj.config :as config]))

;;
;; These operations are possible _only_ with an OAuth token.
;; For now, it has to be passed on every function call, as when building a web application
;; that handles many users at a time, these calls have to be completely isolated and based
;; on current user session rather than general API configuration. Of course, this is less
;; relevant for standalone or single user apps, but for that case it's quite easy to write
;; a small wrapper for all these functions.
;;

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
