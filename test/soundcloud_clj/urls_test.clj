(ns soundcloud-clj.urls-test
  (:use clojure.test
        soundcloud-clj.utils
        soundcloud-clj.test-helper)
  (:require [soundcloud-clj.urls :as urls]))

(initialize-endpoint!)

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



(deftest my-urls-test
  (testing "User URL"
    (is (= "https://api.sandbox-soundcloud.com/me.json" (urls/me))))

  (testing "User Tracks URL"
    (is (= "https://api.sandbox-soundcloud.com/me/tracks.json" (urls/my-tracks))))

  (testing "User Playlists URL"
    (is (= "https://api.sandbox-soundcloud.com/me/playlists.json" (urls/my-playlists))))

  (testing "User Followings URL"
    (is (= "https://api.sandbox-soundcloud.com/me/followings.json" (urls/my-followings)))
    (is (= "https://api.sandbox-soundcloud.com/me/followings/3232.json" (urls/my-followings 3232))))

  (testing "User Followers URL"
    (is (= "https://api.sandbox-soundcloud.com/me/followers.json" (urls/my-followers)))
    (is (= "https://api.sandbox-soundcloud.com/me/followers/3232.json" (urls/my-followers 3232))))

  (testing "User Comments URL"
    (is (= "https://api.sandbox-soundcloud.com/me/comments.json" (urls/my-comments))))

  (testing "User Favorites URL"
    (is (= "https://api.sandbox-soundcloud.com/me/favorites.json" (urls/my-favorites)))
    (is (= "https://api.sandbox-soundcloud.com/me/favorites/3232.json" (urls/my-favorites 3232))))

  (testing "User Groups URL"
    (is (= "https://api.sandbox-soundcloud.com/me/groups.json" (urls/my-groups))))

  (testing "User Web-Profiles URL"
    (is (= "https://api.sandbox-soundcloud.com/me/web-profiles.json" (urls/my-web-profiles)))))