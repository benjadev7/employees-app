package com.babel.benjamin.empleados_app.security.service.impl;

import com.babel.benjamin.empleados_app.security.service.JwtService;
import io.jsonwebtoken.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.www.NonceExpiredException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Service
public class JwtServiceImpl implements JwtService {

    @Override
    public String generateAccessToken(String username,
                                      SecretKey secretKey,
                                      long expirationTime) {
        JwtBuilder jwtBuilder = Jwts.builder();

        Instant now = Instant.now();
        Instant expiration = now.plusSeconds(expirationTime);

        Claims claims = Jwts
                .claims()
                .subject(username)
                .id(UUID.randomUUID().toString())
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiration))
                .build();

        return jwtBuilder
                .claims(claims)
                .signWith(secretKey)
                .compact();
    }

    @Override
    public Claims parseToken(String s, SecretKey secretKey) {
        JwtParser jwtParser =  Jwts.parser()
                .verifyWith(secretKey)
                .build();

        try {
            return jwtParser.parseSignedClaims(s).getPayload();
        } catch (JwtException | IllegalArgumentException e) {
            throw new com.babel.benjamin.empleados_app.exception.JwtException(e.getMessage());
        }
    }
}
