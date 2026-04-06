package com.babel.benjamin.empleados_app.controller;

import com.babel.benjamin.empleados_app.dto.UserDto;
import com.babel.benjamin.empleados_app.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/seed")
    public ResponseEntity<?> seed() {
        log.info("seed loaded.");

        UserDto userDto = new UserDto("admin", "admin123");
        authService.create(userDto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    private ResponseEntity<?> login() {
        return ResponseEntity.ok().build();
    }
}
