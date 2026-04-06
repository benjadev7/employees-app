package com.babel.benjamin.empleados_app.security.service;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class BearerAuthToken extends AbstractAuthenticationToken {
    private String username;
    private String password;

    public BearerAuthToken(Collection<? extends GrantedAuthority> authorities,
                           String username,
                           String credentials, boolean actenticated) {
        super(authorities);

        this.password = credentials;
        this.username = username;
        this.setAuthenticated(actenticated);
    }

    public static BearerAuthToken authenticated(String username) {
        return new BearerAuthToken(null, username, null, true);
    }

    @Override
    public Object getCredentials() {
        return this.password;
    }

    @Override
    public Object getPrincipal() {
        return this.username;
    }
}
