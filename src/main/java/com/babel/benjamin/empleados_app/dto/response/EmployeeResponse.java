package com.babel.benjamin.empleados_app.dto.response;

import com.babel.benjamin.empleados_app.model.Employee;

import java.util.List;

public record EmployeeResponse(List<Employee> employees, String timestamp) {

}
