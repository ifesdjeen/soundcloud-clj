(ns soundcloud-clj.urls
  (:require [soundcloud-clj.config   :as config]))

(def ^:const ^:private users-endpoint "users")
(def ^:const ^:private tracks-endpoint "tracks")
(def ^:const ^:private playlists-endpoint "playlists")
(def ^:const ^:private followings-endpoint "followings")
(def ^:const ^:private followers-endpoint "followers")
(def ^:const ^:private comments-endpoint "comments")
(def ^:const ^:private favorites-endpoint "favorites")
(def ^:const ^:private groups-endpoint "groups")
(def ^:const ^:private web-profiles-endpoint "web-profiles")

(defn base
  []
  (:uri (config/endpoint)))

(def ^:const slash "/")
(def ^:const dotjson ".json")

;;
;; Users
;;

(defn users
  "API endpoint for getting the list of users:

    http://developers.soundcloud.com/docs/api/users
  "
  ([]
     (str (base) slash users-endpoint dotjson))
  ([user-id]
     (str (base) slash users-endpoint slash user-id dotjson)))

(defn user-tracks
  [user-id]
  (str (base) slash users-endpoint slash user-id slash tracks-endpoint dotjson))

(defn user-playlists
  [user-id]
  (str (base) slash users-endpoint slash user-id slash playlists-endpoint dotjson))

(defn user-followings
  ([user-id]
     (str (base) slash users-endpoint slash user-id slash followings-endpoint dotjson))
  ([user-id following-id]
     (str (base) slash users-endpoint slash user-id slash followings-endpoint slash following-id dotjson)))

(defn user-followers
  ([user-id]
     (str (base) slash users-endpoint slash user-id slash followers-endpoint dotjson))
  ([user-id follower-id]
     (str (base) slash users-endpoint slash user-id slash followers-endpoint slash follower-id dotjson)))

(defn user-comments
  [user-id]
  (str (base) slash users-endpoint slash user-id slash comments-endpoint dotjson))

(defn user-favorites
  ([user-id]
     (str (base) slash users-endpoint slash user-id slash favorites-endpoint dotjson))
  ([user-id favorite-id]
     (str (base) slash users-endpoint slash user-id slash favorites-endpoint slash favorite-id dotjson)))

(defn user-groups
  [user-id]
  (str (base) slash users-endpoint slash user-id slash groups-endpoint dotjson))

(defn user-web-profiles
  [user-id]
  (str (base) slash users-endpoint slash user-id slash web-profiles-endpoint dotjson))
