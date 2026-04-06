package com.babel.benjamin.empleados_app.repository;

import com.babel.benjamin.empleados_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
