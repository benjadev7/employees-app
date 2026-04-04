package com.babel.benjamin.empleados_app.service;

import com.babel.benjamin.empleados_app.dto.request.EmployeeRequest;
import com.babel.benjamin.empleados_app.dto.response.EmployeeResponse;
import com.babel.benjamin.empleados_app.dto.response.EmployeesResponse;

import java.util.List;

public interface EmployeeService {
    EmployeesResponse getEmployees();
    void saveEmployees(List<EmployeeRequest> employeeList);
    void deleteEmployeeById(int id);
    EmployeesResponse getEmployees(int id);
    EmployeeResponse updateEmploye(int id, EmployeeRequest employeeRequest);
    EmployeesResponse findByName(String name);
}
