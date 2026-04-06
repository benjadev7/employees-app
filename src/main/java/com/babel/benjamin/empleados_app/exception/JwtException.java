package com.babel.benjamin.empleados_app.exception;

public class JwtException extends RuntimeException {
    public JwtException(String message) {
        super(message);
    }
}
