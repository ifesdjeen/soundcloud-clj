(ns soundcloud-clj.urls-test
  (:use clojure.test  soundcloud-clj.utils)
  (:require [soundcloud-clj.urls :as urls]
            [soundcloud-clj.config :as config]
            ))

(def ^:const client-id (get (System/getenv) "CLIENT_ID"))
(def ^:const client-secret (get (System/getenv) "CLIENT_SECRET"))
(def ^:const base-uri  "http://api.sandbox-soundcloud.com")

(config/set-endpoint! base-uri client-id client-secret)

(deftest users-url-test
  (testing "Users URL"
    (is (= "http://api.sandbox-soundcloud.com/users.json" (urls/users))))

  (testing "User URL"
    (is (= "http://api.sandbox-soundcloud.com/users/3464.json" (urls/users 3464))))

  (testing "User Tracks URL"
    (is (= "http://api.sandbox-soundcloud.com/users/3464/tracks.json" (urls/user-tracks 3464))))

  (testing "User Playlists URL"
    (is (= "http://api.sandbox-soundcloud.com/users/3464/playlists.json" (urls/user-playlists 3464))))

  (testing "User Followings URL"
    (is (= "http://api.sandbox-soundcloud.com/users/3464/followings.json" (urls/user-followings 3464)))
    (is (= "http://api.sandbox-soundcloud.com/users/3464/followings/3232.json" (urls/user-followings 3464 3232))))

  (testing "User Followers URL"
    (is (= "http://api.sandbox-soundcloud.com/users/3464/followers.json" (urls/user-followers 3464)))
    (is (= "http://api.sandbox-soundcloud.com/users/3464/followers/3232.json" (urls/user-followers 3464 3232))))

  (testing "User Comments URL"
    (is (= "http://api.sandbox-soundcloud.com/users/3464/comments.json" (urls/user-comments 3464))))

  (testing "User Favorites URL"
    (is (= "http://api.sandbox-soundcloud.com/users/3464/favorites.json" (urls/user-favorites 3464)))
    (is (= "http://api.sandbox-soundcloud.com/users/3464/favorites/3232.json" (urls/user-favorites 3464 3232))))

  (testing "User Groups URL"
    (is (= "http://api.sandbox-soundcloud.com/users/3464/groups.json" (urls/user-groups 3464))))
  (testing "User Web-Profiles URL"
    (is (= "http://api.sandbox-soundcloud.com/users/3464/web-profiles.json" (urls/user-web-profiles 3464)))))