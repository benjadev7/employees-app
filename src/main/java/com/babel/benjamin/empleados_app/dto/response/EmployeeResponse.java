package com.babel.benjamin.empleados_app.dto.response;

import com.babel.benjamin.empleados_app.model.Employee;
import lombok.Builder;

import java.util.List;

@Builder
public record EmployeeResponse(List<Employee> employees, String timestamp) {

}
