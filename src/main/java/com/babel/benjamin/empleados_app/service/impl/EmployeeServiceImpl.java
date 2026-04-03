package com.babel.benjamin.empleados_app.service.impl;

import com.babel.benjamin.empleados_app.dto.request.EmployeeRequest;
import com.babel.benjamin.empleados_app.dto.response.EmployeeResponse;
import com.babel.benjamin.empleados_app.exception.EmployeeIdNotFoundException;
import com.babel.benjamin.empleados_app.model.Employee;
import com.babel.benjamin.empleados_app.repository.EmployeeRepository;
import com.babel.benjamin.empleados_app.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse getEmployees() {
        return new EmployeeResponse(employeeRepository.findAll(), Instant.now().toString());
    }

    @Override
    public void saveEmployees(List<EmployeeRequest> employeeList) {

        employeeList.forEach(EmployeeRequest::valid);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        List<Employee> employeeModelList = employeeList.stream()
                .map(e -> Employee.builder()
                        .name(e.getName())
                        .middleName(e.getMiddleName())
                        .firstName(e.getFirstName())
                        .lastName(e.getLastName())
                        .age(Integer.parseInt(e.getAge()))
                        .gender(Employee.Gender.valueOf(e.getGender()))
                        .birthDate(LocalDate.parse(e.getBirthDate(), formatter))
                        .position(Employee.Position.valueOf(e.getPosition()))
                        .active(e.getActive())
                        .build())
                .toList();

        employeeRepository.saveAll(employeeModelList);
    }

    @Override
    public void deleteEmployeeById(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        employee.ifPresentOrElse(employeeRepository::delete, () -> {
            throw new EmployeeIdNotFoundException(id);
        });
    }

    @Override
    public EmployeeResponse getEmployees(int id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isPresent()) {
            return EmployeeResponse
                    .builder()
                    .employees(List.of(employeeOptional.get()))
                    .build();
        } else {
            throw new EmployeeIdNotFoundException(id);
        }

    }
}
