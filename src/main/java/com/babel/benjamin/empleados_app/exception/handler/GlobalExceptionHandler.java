package com.babel.benjamin.empleados_app.exception.handler;

import com.babel.benjamin.empleados_app.dto.response.ApiResponse;
import com.babel.benjamin.empleados_app.exception.EmployeeIdNotFoundException;
import com.babel.benjamin.empleados_app.exception.JwtException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.Instant;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /*
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeException(RuntimeException e) {

        int status = 500;
        String mensaje = "Error del servidor";

        return ResponseEntity.status(status).body(mensaje);
    }

     */

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<?> valiationException(HandlerMethodValidationException e) {
        int status = HttpStatus.BAD_REQUEST.value();

        return ResponseEntity
                .status(status)
                .body(new ApiResponse(status,
                        String.valueOf(e.getDetailMessageArguments()[0]),
                        Instant.now().toString()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        int status = HttpStatus.BAD_REQUEST.value();

        return ResponseEntity
                .status(status)
                .body(new ApiResponse(status,
                        e.getMessage(),
                        Instant.now().toString()));
    }

    @ExceptionHandler(EmployeeIdNotFoundException.class)
    public ResponseEntity<?> employeeIdNotFoundException(EmployeeIdNotFoundException e) {
        int status = HttpStatus.BAD_REQUEST.value();

        return ResponseEntity
                .status(status)
                .body(new ApiResponse(status,
                        String.valueOf(e.getMessage()),
                        Instant.now().toString()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        int status = HttpStatus.BAD_REQUEST.value();

        return ResponseEntity
                .status(status)
                .body(new ApiResponse(status,
                        e.getMessage(),
                        Instant.now().toString()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> authenticationException(AuthenticationException e) {
        int status = HttpStatus.BAD_REQUEST.value();

        return ResponseEntity
                .status(status)
                .body(new ApiResponse(status,
                        e.getMessage(),
                        Instant.now().toString()));
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<?> jwtException(JwtException e) {
        int status = HttpStatus.UNAUTHORIZED.value();

        return ResponseEntity
                .status(status)
                .body(new ApiResponse(status,
                        e.getMessage(),
                        Instant.now().toString()));
    }
}
