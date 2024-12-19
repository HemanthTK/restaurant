package com.manage.restaurant.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private String secretKey = "mySecretKey";  // Same as before
    private long expirationTime = 3600000;  // 1 hour expiration

    // Generate JWT
    public String generateToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(algorithm);
    }

    // Decode JWT
    public DecodedJWT decodeToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.require(algorithm)
                .build()
                .verify(token);
    }

    // Extract username from token
    public String extractUsername(String token) {
        return decodeToken(token).getSubject();
    }

    // Validate token
    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !decodeToken(token).getExpiresAt().before(new Date()));
    }
}

