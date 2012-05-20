(ns soundcloud-clj.users-test
  (:use clojure.test
        soundcloud-clj.test-helper
        clj-http.fake)
  (:require [soundcloud-clj.users :as users]))

(initialize-endpoint!)

(deftest ^{ :network-bound true } get-users-test
  (testing "Get users API request"
    (let [first-page-response      (users/get-users)
          second-page-response     (users/get-users :page 2)
          response-item            (first first-page-response)]
      (is (= 10 (count first-page-response)))
      (is (= 10 (count second-page-response)))
      (is (not (= (first first-page-response) (first second-page-response))))
      (doseq [field (keys response-item)]
        (is (some #(= field %) user-fields))))))

(deftest ^{ :network-bound true } get-users-search-test
  (testing "Get users API request"
    (let [response      (users/get-users :q "tricknik")
          response-item (first response)]
      (is (= 1 (count response)))
      (doseq [field (keys response-item)]
        (is (some #(= field %) user-fields))))))

(deftest ^{ :network-bound true } get-user-test
  (testing "Get users API request"
    (let [user          (users/get-user 1888197)]
      (doseq [field (keys user)]
        (is (some #(= field %) user-fields))))))

(deftest ^{ :network-bound true } get-user-tracks
  (testing "Get user tracks API request"
    (let [tracks          (users/get-user-tracks 3207)
          track           (first tracks)]
      (is (= 10 (count tracks)))
      (doseq [field (keys track)]
        (is (some #(= field %) track-fields))))))

(deftest ^{ :network-bound true } get-user-followings
  (testing "Get user followings API request"
    (let [followings          (users/get-user-followings 3207)
          following           (first followings)]
      (is (= 10 (count followings)))
      (doseq [field (keys following)]
        (is (some #(= field %) user-fields))))))

(deftest ^{ :network-bound true } get-user-followers
  (testing "Get user followers API request"
    (let [followers          (users/get-user-followers 3207)
          following           (first followers)]
      (is (= 10 (count followers)))
      (doseq [field (keys following)]
        (is (some #(= field %) user-fields))))))


(deftest ^{ :fake-api true :focus true } get-users-2-test
  (testing "Get users API request"
    (with-fake-routes-in-isolation   fake-routes
      (let [first-page-response      (users/get-users)
            second-page-response     (users/get-users :page 2)
            response-item            (first first-page-response)]
        (is (= 10 (count first-page-response)))
        (is (= 10 (count second-page-response)))
        (is (not (= (first first-page-response) (first second-page-response))))
        (doseq [field (keys response-item)]
          (is (some #(= field %) user-fields)))))))
