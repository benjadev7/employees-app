package com.babel.benjamin.empleados_app.dto.response;


import com.babel.benjamin.empleados_app.model.Employee;
import lombok.Builder;

import java.sql.Timestamp;
import java.time.LocalDate;

@Builder
public record EmployeeResponse(
        int id,
        String name,
        String middleName,
        String firstName,
        String lastName,
        int age,
        Employee.Gender gender,
        LocalDate birthDate,
        Employee.Position position,
        Timestamp createdAt,
        boolean active
        ) {
}
