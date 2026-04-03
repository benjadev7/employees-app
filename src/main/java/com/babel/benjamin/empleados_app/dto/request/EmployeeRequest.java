package com.babel.benjamin.empleados_app.dto.request;

import com.babel.benjamin.empleados_app.model.Employee;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;


@Getter
@Setter
@Slf4j
public class EmployeeRequest {
    @NotBlank
    private String name;
    private String middleName;
    @NotBlank
    private String firstName;
    private String lastName;
    @NotBlank
    @Pattern(regexp = "^[0-9]+$")
    private String age;
    @NotBlank
    private String gender;
    @NotBlank
    @Pattern(
            regexp = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-[0-9]{4}$",
            message = "must be like dd-MM-yyyy")
    private String birthDate;
    @NotBlank
    private String position;
    private Boolean active;

    public void valid() {
        try {
            Employee.Gender genderEn = Employee.Gender.valueOf(gender.toUpperCase());
            Employee.Position positionEn = Employee.Position.valueOf(position.toUpperCase());

            this.gender = genderEn.toString();
            this.position = positionEn.toString();
        } catch (IllegalArgumentException e) {
            gender = Employee.Gender.OTHER.toString();
            position = Employee.Position.DEVELOPER.toString();
        }
    }
}
