package com.babel.benjamin.empleados_app.service;

import com.babel.benjamin.empleados_app.dto.response.EmployeeResponse;
import com.babel.benjamin.empleados_app.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    EmployeeResponse getEmployees();
}
