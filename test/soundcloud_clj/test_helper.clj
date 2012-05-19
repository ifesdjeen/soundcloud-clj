(ns soundcloud-clj.test-helper
  (:require [soundcloud-clj.config :as config]))


(def ^:const client-id (get (System/getenv) "CLIENT_ID"))
(def ^:const client-secret (get (System/getenv) "CLIENT_SECRET"))
(def ^:const oauth-token (get (System/getenv) "OAUTH_TOKEN"))

(def ^:const base-uri  "http://api.sandbox-soundcloud.com")
(def ^:const base-secure-uri  "https://api.sandbox-soundcloud.com")

(defn initialize-endpoint!
  []
  (config/set-endpoint! base-uri base-secure-uri client-id client-secret))
