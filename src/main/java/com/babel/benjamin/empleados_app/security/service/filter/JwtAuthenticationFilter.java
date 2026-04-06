package com.babel.benjamin.empleados_app.security.service.filter;

import com.babel.benjamin.empleados_app.security.service.BearerAuthToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String requestHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        Optional<String> token = extractToken(requestHeader);

        if (token.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        BearerAuthToken bearerAuthToken = new BearerAuthToken(List.of(), "", token.get(), false);

        Authentication authentication = authenticationManager.authenticate(bearerAuthToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    private Optional<String> extractToken(String authorizationHeader) {
        if (!StringUtils.hasText(authorizationHeader)) return Optional.empty();
        return Optional.of(authorizationHeader.substring(7));
    }
}
