package com.babel.benjamin.empleados_app.security.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.util.Collection;

public interface JwtService {
    String generateAccessToken(String usarname, SecretKey secretKey, long expirationTimeAccessToken);
    Claims parseToken(String s, SecretKey secretKey);
}
