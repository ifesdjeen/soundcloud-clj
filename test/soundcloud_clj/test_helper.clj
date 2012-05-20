(ns soundcloud-clj.test-helper
  (:require [clojure.java.io       :as io]
            [soundcloud-clj.config :as config]))


(def ^:const client-id (get (System/getenv) "CLIENT_ID"))
(def ^:const client-secret (get (System/getenv) "CLIENT_SECRET"))
(def ^:const oauth-token (get (System/getenv) "OAUTH_TOKEN"))

(def ^:const base-uri  "http://api.sandbox-soundcloud.com")
(def ^:const base-secure-uri  "https://api.sandbox-soundcloud.com")

(defn initialize-endpoint!
  []
  (config/set-endpoint! base-uri base-secure-uri client-id client-secret))

(def ^:const user-fields [ :id :kind :permalink :username :uri :permalink-url :avatar-url :country :full-name :city :description :discogs-name :myspace-name :website :website-title :online :track-count :playlist-count :followers-count :followings-count :public-favorites-count :avatar-data])

(def ^:const track-fields [ :license :artwork-url :duration :release-year :label-id :kind :state :sharing :user-id :playback-count :label-name :waveform-url :bpm :purchase-title :permalink-url :download-count :downloadable :isrc :favoritings-count :streamable :title :uri :release-day :permalink :genre :original-content-size :video-url :release-month :release :commentable :created-at :user :comment-count :key-signature :purchase-url :stream-url :attachments-uri :id :track-type :tag-list :description :original-format ])

(def ^:const me-fields [ :country :private-tracks-count :plan :kind :avatar-url :discogs-name :track-count :permalink-url :full-name :public-favorites-count :private-playlists-count :uri :city :username :permalink :followers-count :playlist-count :online :myspace-name :followings-count :primary-email-confirmed :id :website :website-title :description])

(def ^:const my-track-fields [ :license :artwork-url :download-url :duration :release-year :downloads-remaining :label-id :kind :state :sharing :user-id :playback-count :user-favorite :label-name :waveform-url :bpm :purchase-title :permalink-url :download-count :downloadable :isrc :favoritings-count :streamable :title :uri :release-day :permalink :genre :secret-token :original-content-size :video-url :release-month :release :commentable :user-playback-count :created-at :user :comment-count :key-signature :purchase-url :stream-url :attachments-uri :secret-uri :id :track-type :tag-list :description :original-format ])

(def ^:const fake-routes
  { (format "http://api.sandbox-soundcloud.com/users.json?client_id=%s&offset=0&limit=10" client-id)
    (fn [request] {:status 200 :headers {:content-type "application/json"}
                   :body (slurp (io/resource (str "api/users.json"))) })

    (format "http://api.sandbox-soundcloud.com/users.json?offset=10&client_id=%s&limit=10" client-id)
    (fn [request] {:status 200 :headers {:content-type "application/json"}
                   :body (slurp (io/resource (str "api/users_page_2.json"))) })
  })