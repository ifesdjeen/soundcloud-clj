(ns soundcloud-clj.urls
  (:require [soundcloud-clj.config   :as config]))

(def ^:const ^:private users-endpoint "users")
(def ^:const ^:private me-endpoint "me")
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

(defn secure-base
  []
  (:secure-uri (config/endpoint)))

(def ^:const slash "/")
(def ^:const dotjson ".json")

;;
;; Users
;;
;; http://developers.soundcloud.com/docs/api/users
;;

(defn users
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


;;
;; Me
;;
;; http://developers.soundcloud.com/docs/api/me
;;

(defn me
  ([]
     (str (secure-base) slash me-endpoint dotjson)))

(defn my-tracks
  []
  (str (secure-base) slash me-endpoint slash tracks-endpoint dotjson))

(defn my-playlists
  []
  (str (secure-base) slash me-endpoint slash playlists-endpoint dotjson))

(defn my-followings
  ([]
     (str (secure-base) slash me-endpoint slash followings-endpoint dotjson))
  ([ following-id]
     (str (secure-base) slash me-endpoint slash followings-endpoint slash following-id dotjson)))

(defn my-followers
  ([]
     (str (secure-base) slash me-endpoint slash followers-endpoint dotjson))
  ([follower-id]
     (str (secure-base) slash me-endpoint slash followers-endpoint slash follower-id dotjson)))

(defn my-comments
  []
  (str (secure-base) slash me-endpoint slash comments-endpoint dotjson))

(defn my-favorites
  ([]
     (str (secure-base) slash me-endpoint slash favorites-endpoint dotjson))
  ([ favorite-id]
     (str (secure-base) slash me-endpoint slash favorites-endpoint slash favorite-id dotjson)))

(defn my-groups
  []
  (str (secure-base) slash me-endpoint slash groups-endpoint dotjson))

(defn my-web-profiles
  []
  (str (secure-base) slash me-endpoint slash web-profiles-endpoint dotjson))

