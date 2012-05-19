(ns soundcloud-clj.users-test
  (:use clojure.test  soundcloud-clj.utils)
  (:require [soundcloud-clj.users :as users]
            [soundcloud-clj.config :as config]))

(def ^:const client-id (get (System/getenv) "CLIENT_ID"))
(def ^:const client-secret (get (System/getenv) "CLIENT_SECRET"))
(def ^:const base-uri  "http://api.sandbox-soundcloud.com")

(config/set-endpoint! base-uri client-id client-secret)

(deftest ^{ :network-bound true } get-users-test
  (testing "Get users API request"
    (let [response (users/get-users)
          response-item (first (users/get-users))
          user-fields   [ :id :kind :permalink :username :uri :permalink-url :avatar-url :country :full-name :city :description :discogs-name :myspace-name :website :website-title :online :track-count :playlist-count :followers-count :followings-count :public-favorites-count :avatar-data 1] ]
      (is (= 50 (count response)))
      (doseq [field (keys response-item)]
        (is (some #(= field %) user-fields))))))

;; (deftest get-user-test
;;   (testing "Get users API request"
;;     (let [response (users/get-user 1864467)
;;           response-item (first (users/get-users))
;;           user-fields   [ :id :kind :permalink :username :uri :permalink-url :avatar-url :country :full-name :city :description :discogs-name :myspace-name :website :website-title :online :track-count :playlist-count :followers-count :followings-count :public-favorites-count :avatar-data 1] ]
;;       (is (= 50 (count response)))
;;       (doseq [field (keys response-item)]
;;         (is (some #(= field %) user-fields))))))
