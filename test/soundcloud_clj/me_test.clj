(ns soundcloud-clj.me-test
  (:use clojure.test
        soundcloud-clj.test-helper)
  (:require [soundcloud-clj.me :as me]
            [soundcloud-clj.config :as config]))

(def ^:const me-fields [ :country :private-tracks-count :plan :kind :avatar-url :discogs-name :track-count :permalink-url :full-name :public-favorites-count :private-playlists-count :uri :city :username :permalink :followers-count :playlist-count :online :myspace-name :followings-count :primary-email-confirmed :id :website :website-title :description])

(deftest ^{ :network-bound true :focus true } get-me-test
  (testing "Get users API request"
    (let [me          (me/get-me oauth-token)]
      (doseq [field (keys me)]
        (is (some #(= field %) me-fields))))))

