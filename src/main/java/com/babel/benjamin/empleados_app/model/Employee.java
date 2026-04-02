package com.babel.benjamin.empleados_app.model;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Employee {
    private String name;
    private String secondName;
    private String firstName;
    private String lastName;
    private Integer age;
    private String sex;
    private String birth;
    private String puesto;
    private Date createdAt;
    private Boolean active;
}
