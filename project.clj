(defproject soundcloud-clj "0.1.0-SNAPSHOT"
  :description "Clojure Soundcloud client, hack your Clojure Soundcloud app with ease!"
  :url "http://github.com/ifesdjeen/soundcloud-clj"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure    "1.3.0"]
                 [clj-http               "0.4.1" :exclusions [org.clojure/clojure]]
                 [clj-http-fake          "0.3.0"]
                 [org.clojure/data.json  "0.1.2"]]
  :profiles     {:dev {:resource-paths ["test/resources"]}}
  :test-selectors {:default (fn [v] (not (:network-bound v)))
                   :all            (constantly true)
                   :network-bound  :network-bound
                   :fake-api       :fake-api
                   :focus          :focus})