# AUTHENTICATION MICROSERVICE FOR ROBO CHESS

Authentication to the site is provided by OpenID, while authorisation is provided by a mixture of OpenId and oAuth.

A user that has been verified by a partner site can gain access to their account without having to re-authenticate
using oAuth.  However to log into their account without prior verification on a partner site they will need to use
OpenID.

# DESIGN ROUGH

```
  
  client -> authenticator -> Zuul -> RoboChess
  
```

## Create

```
  Client        authServer           Proxy Server
    |               |                  |
    |  User         |                  |
    |-------------->|                  |
    |               |--+ addUser()     |
    |               |  |               |
    |               |<-+               |
    |               |--+ createToken() |
    |               |  |               |
    |               |<-+               |
    |  token        |                  |
    |<--------------|                  |
    |               |                  |
    | socketconnect |                  |
    |--------------------------------->|
```

## Request

```
  Client        authServer    RoboChess 
    |               |             |
    | token/Request |             |
    |-------------->|             |
    |               |             |
    |               |--+ verify() |
    |               |  |          |
    |               |<-+          |
    |               |  request    |
    |               |------------>|
    |               |             |
    |  websocket response         |
    |<----------------------------|
    |               |             |
```




## Test Request

```
curl -H 'Content-Type: application/json' -X PUT -d '{"username":"foo"}' http://localhost:8080/user/registration

curl -H 'Content-Type: application/json' -X POST -d 'username=foo' http://localhost:8080/user/registration

curl -X POST -d 'username=foo' http://localhost:8080/user/registration

curl -X PUT -d 'username=foo' http://localhost:8080/user/registration

curl -H 'Content-Type: application/json' -X POST -d '{"username":"foo"}' http://localhost:8080/user/registration

curl  -X POST -d '{"username":"foo"}' http://localhost:8080/user/registration


curl -X POST -d 'username=foo' http://localhost:8080/user/registration

curl -vX POST  -H  "Content-Type: application/json" -d '{"username" : "foo"}' http://localhost:8080/user/registration

curl -vX POST http://localhost:8080/user/registration -d @testplace.json

curl --http2 -i -vX POST  -H  "Content-Type: application/json" --data-binary @user.json http://localhost:8080/user/registration
 
curl -i -vX POST  -H "Accept: application/json" -H "Content-type: application/json" --data-binary @user.json http://localhost:8080/user/registration
```