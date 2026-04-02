package com.babel.benjamin.empleados_app.service.impl;

import com.babel.benjamin.empleados_app.dto.response.EmployeeResponse;
import com.babel.benjamin.empleados_app.model.Employee;
import com.babel.benjamin.empleados_app.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public EmployeeResponse getEmployees() {

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(Employee.builder()
                .name("benja")
                        .age(38)
                        .birth("20-07-1988")
                        .sex("M")
                        .active(true)
                .build());
        employeeList.add(Employee.builder()
                .name("ana")
                .age(25)
                .birth("20-07-1988")
                .sex("M")
                .active(true)
                .build());
        employeeList.add(Employee.builder()
                .name("luis")
                .age(40)
                .birth("20-07-1988")
                .sex("M")
                .active(true)
                .build());



        log.info("logica de negocio");
        return new EmployeeResponse(employeeList, Instant.now().toString());
    }
}
