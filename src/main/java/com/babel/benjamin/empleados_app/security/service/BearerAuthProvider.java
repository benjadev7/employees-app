package com.babel.benjamin.empleados_app.security.service;

import com.babel.benjamin.empleados_app.security.service.impl.JwtServiceImpl;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BearerAuthProvider implements AuthenticationProvider {
    private final SecretKey secretKey;
    private final JwtService jwtService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Claims claims = jwtService.parseToken((authentication instanceof BearerAuthToken authToken) ? (String)
                authToken.getCredentials() : "", secretKey);

        String username = claims.get(Claims.SUBJECT, String.class);

        return BearerAuthToken.authenticated(username);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(BearerAuthToken.class);
    }
}
