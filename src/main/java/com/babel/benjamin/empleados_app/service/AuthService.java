package com.babel.benjamin.empleados_app.service;

import com.babel.benjamin.empleados_app.dto.UserDto;
import com.babel.benjamin.empleados_app.dto.request.LoginRequest;
import com.babel.benjamin.empleados_app.dto.response.AuthResponse;

public interface AuthService {
    void create(UserDto user);
    AuthResponse login(LoginRequest loginRequest);
}
