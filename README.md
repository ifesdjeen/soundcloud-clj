# Soundcloud Clojure API wrapper

This is a very Clojure wrapper for [Soundcloud API](http://developers.soundcloud.com/docs/api).
Want to hack a Soundcloud-based app in Clojure? Most likely that'd be a good use for you.

__Why HTTP requests were not mocked?__ client should guarantee consistency as much as it's possible.
As there's no way to choose which version of API endpoint is used, it's better to see that tests
have failed rather than mock everything and be happy they pass, but things do not work in reality.
Nowadays most of people have a good bandwith, and for tests you use your own API key, so library
developers won't get banned for you :)

## Usage

### Connecting

In order to connect, you have to register your application first. To do that, visit [Apps Page](soundcloud.com/you/apps).
Next, set API endpoint:

```clojure
(ns my-awesome-app.core
  (:require [soundcloud-clj.config :as config]))

(def ^:const client-id (get (System/getenv) "CLIENT_ID"))
(def ^:const client-secret (get (System/getenv) "CLIENT_SECRET"))
(def ^:const base-uri  "http://api.sandbox-soundcloud.com")

(config/set-endpoint! "http://api.sandbox-soundcloud.com" "YOUR_CLIENT_KEY" "YOUR_CLIENT_SECRET")
```

### Basic operations on users:

Get list of users:

```clojure
(users/get-users)
```

Get user with id 3423:

```clojure
(users/get-users 3423)
```

## Running tests

As tests rely on API endpoint, they're separated in two parts. To run regular test suite that does not hit Soundcloud API, run:

```
lein2 test
```

In order to run tests that are hitting API endpoint, run:

```
CLIENT_ID=<YOUR_CLIENT_ID> CLIENT_SECRET=<YOUR_CLIENT_SECRET> lein2 test :network-bound
```

## License

Copyright © 2012 Alex P

This library is distributed under the Eclipse Public License, the same as Clojure.

Soundcloud as a Trademark and API itself, belong to their respective owners.
