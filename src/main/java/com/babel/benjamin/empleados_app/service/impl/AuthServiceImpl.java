package com.babel.benjamin.empleados_app.service.impl;

import com.babel.benjamin.empleados_app.config.JwtProps;
import com.babel.benjamin.empleados_app.dto.UserDto;
import com.babel.benjamin.empleados_app.dto.request.LoginRequest;
import com.babel.benjamin.empleados_app.dto.response.AuthResponse;
import com.babel.benjamin.empleados_app.model.User;
import com.babel.benjamin.empleados_app.repository.UserRepository;
import com.babel.benjamin.empleados_app.security.service.JwtService;
import com.babel.benjamin.empleados_app.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final SecretKey secretKey;
    private final JwtProps jwtProps;

    @Override
    public void create(UserDto userDto) {

        Optional<User> userOptional = userRepository.findByUsername(userDto.username());

        if (userOptional.isEmpty()) {
            User user = User.builder()
                    .password(passwordEncoder.encode(userDto.password()))
                    .username(userDto.username())
                    .build();

            userRepository.save(user);
        }
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken unauthenticated = UsernamePasswordAuthenticationToken
                .unauthenticated(loginRequest.getUsername(), loginRequest.getPassword());

        Authentication unauthentica = authenticationManager.authenticate(unauthenticated);
        String token = jwtService.generateAccessToken(unauthentica.getName(),
                secretKey,
                jwtProps.getExpirationTimeAccessToken());

        return new AuthResponse(
                token, jwtProps.getExpirationTimeAccessToken(), Instant.now().toString());

    }
}
