(ns soundcloud-clj.config)

(defrecord SoundCloudEndpoint
    [uri client-id client-secret])

(def ^{:dynamic true} *endpoint*)

(defn endpoint
  []
  *endpoint*)

(def ^:const per-page 10)

(defn set-endpoint!
  "Alters default SoundCloud connection endpoint"
  [uri client-id client-secret]
  (alter-var-root (var *endpoint*) (constantly (SoundCloudEndpoint. uri client-id client-secret))))

