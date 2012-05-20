(ns soundcloud-clj.me-test
  (:use clojure.test
        soundcloud-clj.test-helper)
  (:require [soundcloud-clj.me :as me]
            [soundcloud-clj.config :as config]))

;;
;; These tests are, of course, only relevant to me :/
;;

(deftest ^{ :network-bound true } me-test
  (testing "Get /me API request"
    (let [me          (me/me oauth-token)]
      (doseq [field (keys me)]
        (is (some #(= field %) me-fields))))))


(deftest ^{ :network-bound true } my-tracks-test
  (testing "Get my-tracks API request"
    (let [my-tracks       (me/my-tracks oauth-token)
          track           (first my-tracks)]
      (doseq [field (keys track)]
        (is (some #(= field %) my-track-fields))))))

(deftest ^{ :network-bound true } followings-test
  (testing "Get my-tracks API request"
    (let [user-id 1888239]
      (me/unfollow-user user-id oauth-token)
      (is (not (me/following? user-id oauth-token)))
      (is (= 0 (count (me/my-followings oauth-token))))
      (me/follow-user user-id oauth-token)
      (is (me/following? user-id oauth-token))
      (is (= 1 (count (me/my-followings oauth-token))))
      (me/unfollow-user user-id oauth-token)
      (is (= 0 (count (me/my-followings oauth-token)))))))
