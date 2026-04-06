package com.babel.benjamin.empleados_app.dto.response;

public record AuthResponse(String token, long expiredSecond, String timestamp) {
}
