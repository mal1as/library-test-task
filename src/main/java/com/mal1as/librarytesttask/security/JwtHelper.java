package com.mal1as.librarytesttask.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mal1as.librarytesttask.model.entity.User;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

import static com.mal1as.librarytesttask.utils.ErrorMessages.INVALID_TOKEN_MESSAGE;

@Component
public class JwtHelper {

    @Value("${app.token.jwtSecret}")
    private String secret;
    @Value("${app.token.jwtExpirationMs}")
    private Long expirationMs;
    @Value("${app.token.jwtRefreshExpirationMs}")
    private Long refreshExpirationMs;

    public String generateAccessToken(User user) {
        return JWT.create()
                .withSubject(user.getId().toString())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().getTime() + expirationMs))
                .sign(Algorithm.HMAC256(secret));
    }

    public String generateRefreshToken(User user) {
        return JWT.create()
                .withSubject(user.getId().toString())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date((new Date()).getTime() + refreshExpirationMs))
                .sign(Algorithm.HMAC256(secret));
    }

    public boolean validateToken(String token) {
        return decodeToken(token).isPresent();
    }

    public String getUserIdFromToken(String token) {
        return decodeToken(token)
                .orElseThrow(() -> new ValidationException(String.format(INVALID_TOKEN_MESSAGE, token)))
                .getSubject();
    }

    private Optional<DecodedJWT> decodeToken(String token) {
        try {
            return Optional.of(JWT.require(Algorithm.HMAC256(secret)).build().verify(token));
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }
}
