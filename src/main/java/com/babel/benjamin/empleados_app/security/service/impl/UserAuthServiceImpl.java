package com.babel.benjamin.empleados_app.security.service.impl;

import com.babel.benjamin.empleados_app.adapter.SecurityUser;
import com.babel.benjamin.empleados_app.model.User;
import com.babel.benjamin.empleados_app.repository.UserRepository;
import com.babel.benjamin.empleados_app.security.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findByUsername(username);
        userOptional.orElseThrow(() -> new UsernameNotFoundException("Username: %s not found.".formatted(username)));

        return new SecurityUser(userOptional.get());
    }
}
