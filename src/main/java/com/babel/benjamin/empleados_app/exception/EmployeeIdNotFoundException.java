package com.babel.benjamin.empleados_app.exception;

public class EmployeeIdNotFoundException extends RuntimeException {
    public EmployeeIdNotFoundException(int id) {
        super("id: " + id + " Employee id not found!");
    }
}
