package com.babel.benjamin.empleados_app.service.impl;

import com.babel.benjamin.empleados_app.dto.UserDto;
import com.babel.benjamin.empleados_app.repository.UserRepository;
import com.babel.benjamin.empleados_app.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Override
    public void create(UserDto user) {
        log.info("creando al usuario");
    }
}
