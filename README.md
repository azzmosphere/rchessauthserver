# AUTHENTICATION MICROSERVICE FOR ROBO CHESS

Authentication to the site is provided by OpenID, while authorisation is provided by a mixture of OpenId and oAuth.

A user that has been verified by a partner site can gain access to their account without having to re-authenticate
using oAuth.  However to log into their account without prior verification on a partner site they will need to use
OpenID.