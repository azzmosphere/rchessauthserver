package au.azzmosphere.auth.services;

import au.azzmosphere.auth.persistence.enitites.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class RChessJwtTokenFactory {

    public String createJwtToken(User user) {
        String jwtToken = Jwts.builder().setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey")
                .compact();

        return jwtToken;
    }
}
