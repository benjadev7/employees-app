package com.babel.benjamin.empleados_app.controller;

import com.babel.benjamin.empleados_app.dto.UserDto;
import com.babel.benjamin.empleados_app.dto.request.LoginRequest;
import com.babel.benjamin.empleados_app.dto.response.ApiResponse;
import com.babel.benjamin.empleados_app.dto.response.AuthResponse;
import com.babel.benjamin.empleados_app.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

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

        ApiResponse apiResponse = new ApiResponse(HttpStatus.CREATED.value(),
                "Seed loaded!",
                Instant.now().toString());

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    private ResponseEntity<?> login(
            @RequestBody
            @Valid LoginRequest loginRequest) {

        AuthResponse response = authService.login(loginRequest);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
