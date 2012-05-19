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

(config/set-endpoint! "http://api.sandbox-soundcloud.com" "YOUR_CLIENT_KEY" "YOUR_CLIENT_SECRET")
```

### Basic operations on users:

Get list of users (will return first page of all the page results):

```clojure
(users/get-users)
```

Get the second page of all existing users:

```clojure
(users/get-users :page 2)
```

Get user with id 3423:

```clojure
(users/get-user 3423)
```

Search for the user, that'd match 'ifesdjeen'

```clojure
(users/get-users :q "ifesdjeen") ;; in case lots of results are returned, you can always paginate by adding :page <page_number> param.
```

List tracks/followings/followers/playlists for user with id 3207:

```clojure
(users/get-user-tracks 3207)
(users/get-user-playlists 3207)
(users/get-user-followings 3207)
(users/get-user-followers 3207)

;; and with pagination:
(users/get-user-tracks 3207 :page 2) ;; etc...
```

### Basic operations on /me

These operations are possible _only_ with an OAuth token.
For now, it has to be passed on every function call, as when building a web application
that handles many users at a time, these calls have to be completely isolated and based
on current user session rather than general API configuration. Of course, this is less
relevant for standalone or single user apps, but for that case it's quite easy to write
a small wrapper for all these functions.

Include/require ns:

```clojure
(ns soundcloud-clj.me-test
  (:require [soundcloud-clj.me :as me]))
```

Getting my info:

```clojure
(me/me oauth-token)
```

Getting my tracks:

```clojure
(me/my-tracks oauth-token)
```

Follow, unfollow and get followings:
```clojure
(me/follow-user user-id oauth-token)
(me/unfollow-user user-id oauth-token)
(me/my-followings oauth-token)
```

## Running tests

As tests rely on API endpoint, they're separated in two parts. To run regular test suite that does not hit Soundcloud API, run:

```
lein2 test
```

In order to run tests that are hitting API endpoint, run:

```
OAUTH_TOKEN=<YOUR_OAUTH_ID> CLIENT_ID=<YOUR_CLIENT_ID> CLIENT_SECRET=<YOUR_CLIENT_SECRET> lein2 test :network-bound
```

## License

Copyright © 2012 Alex P

This library is distributed under the Eclipse Public License, the same as Clojure.

Soundcloud as a Trademark and API itself, belong to their respective owners.
