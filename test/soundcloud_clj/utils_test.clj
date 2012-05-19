(ns soundcloud-clj.utils-test
  (:use clojure.test
        soundcloud-clj.test-helper
        soundcloud-clj.utils))

(deftest dasherize-test
  (testing "Dasherize for string value"
    (is (= "hey-ho" (dasherize "hey_ho"))))
  (testing "Dasherize for keyword value"
    (is (= :hey-ho (dasherize :hey_ho))))
  (testing "Dasherize for maps value"
    (is (= {:key-1 1 :key-2 2} (dasherize {:key_1 1 :key_2 2 })))))

(deftest underscore-test
  (testing "Underscore for string value"
    (is (= "hey_ho" (underscore "hey-ho"))))
  (testing "Underscore for keyword value"
    (is (= :hey_ho (underscore :hey-ho))))
  (testing "Underscore for maps value"
    (is (= {:key_1 1 :key_2 2} (underscore {:key-1 1 :key-2 2 }))))
  (testing "Underscore for vector"
    (is (= [{:key_1 1 :key_2 2} {:key_3 3 :key_4 4} ] (underscore [{:key-1 1 :key-2 2 } {:key-3 3 :key-4 4 }])))))


(deftest discard-nils-test
  (testing "Discards nils"
    (is (= {:b "b-value" :d "d-value"} (discard-nils {:a nil :b "b-value" :c nil :d "d-value"})))))

(deftest paginate-test
  (testing "Adds pagination params when page option is given"
    (is (= {:offset 20 :limit 10 :other-option "other-value"} (paginate {:page 3 :other-option "other-value"}))))
  (testing "Adds first page as a default when page option is given"
    (is (= {:offset 0 :limit 10 :other-option "other-value"} (paginate {:other-option "other-value"})))))

(deftest add-client-id-test
  (testing "Adds client_id to hash"
    (is (= {:client_id client-id} (add-client-id {})))))

(deftest transform-query-params-test
  (testing "Transforms params"
    (is (= {:client_id client-id
            :offset 20
            :limit 10
            :other-option "other-value"}
           (transform-query-params {:page 3 :other-option "other-value" :q nil}))))

  (testing "Transforms params"
    (is (= {:oauth_token oauth-token
            :offset 20
            :limit 10
            :other-option "other-value"}
           (transform-query-params {:page 3 :other-option "other-value" :q nil} oauth-token)))))
