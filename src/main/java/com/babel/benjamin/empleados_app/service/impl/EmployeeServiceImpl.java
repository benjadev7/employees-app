package com.babel.benjamin.empleados_app.service.impl;

import com.babel.benjamin.empleados_app.dto.request.EmployeeRequest;
import com.babel.benjamin.empleados_app.dto.response.EmployeeResponse;
import com.babel.benjamin.empleados_app.dto.response.EmployeesResponse;
import com.babel.benjamin.empleados_app.exception.EmployeeIdNotFoundException;
import com.babel.benjamin.empleados_app.model.Employee;
import com.babel.benjamin.empleados_app.repository.EmployeeRepository;
import com.babel.benjamin.empleados_app.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeesResponse getEmployees() {
        return new EmployeesResponse(employeeRepository.findAll(), Instant.now().toString());
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
    public EmployeesResponse getEmployees(int id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isPresent()) {
            return EmployeesResponse
                    .builder()
                    .employees(List.of(employeeOptional.get()))
                    .timestamp(Instant.now().toString())
                    .build();
        } else {
            throw new EmployeeIdNotFoundException(id);
        }

    }

    @Override
    public EmployeeResponse updateEmploye(int id, EmployeeRequest employeeRequest) {
        employeeRequest.valid();

        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        if (employeeOptional.isPresent()) {

            Employee employee = employeeOptional.get();
            employee.setName(employeeRequest.getName());
            employee.setMiddleName(employeeRequest.getMiddleName());
            employee.setFirstName(employeeRequest.getFirstName());
            employee.setLastName(employeeRequest.getLastName());
            employee.setAge(Integer.parseInt(employeeRequest.getAge()));
            employee.setGender(Employee.Gender.valueOf(employeeRequest.getGender()));
            employee.setBirthDate(LocalDate.parse(employeeRequest.getBirthDate(), formatter));
            employee.setActive(employeeRequest.getActive());

            employeeRepository.save(employee);


            return EmployeeResponse
                    .builder()
                    .id(employee.getId())
                    .name(employee.getName())
                    .middleName(employee.getMiddleName())
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .age(employee.getAge())
                    .gender(employee.getGender())
                    .birthDate(employee.getBirthDate())
                    .position(employee.getPosition())
                    .createdAt(employee.getCreatedAt())
                    .active(employee.getActive())
                    .build();
        } else {
            throw new EmployeeIdNotFoundException(id);
        }
    }

    @Override
    public EmployeesResponse findByName(String name) {
        List<Employee>  employees = employeeRepository.findEmployeesByName(name);

        return EmployeesResponse.builder()
                .timestamp(Instant.now().toString())
                .employees(employees)
                .build();
    }
}
