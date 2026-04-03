package com.babel.benjamin.empleados_app.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String middleName;
    private String firstName;
    private String lastName;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Position position;
    @CreationTimestamp
    private Timestamp createdAt;
    private Boolean active;

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    public enum Position {
        DEVELOPER, MANAGER, TESTER
    }
}
