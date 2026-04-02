package com.babel.benjamin.empleados_app.controller;

import com.babel.benjamin.empleados_app.model.Employee;
import com.babel.benjamin.empleados_app.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    /**
     * devuelve el listado de todos los empleados registrados
     * @return ResponseEntity lista de todos los empleados (igual ver paginacion)
     */
    @GetMapping("/employees")
    public ResponseEntity<?> getEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    /**
     * recupera el detalle de un empleado mediante su id
     * @param id id de empleado
     * @return
     */
    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok("implementacion de getEmployee por id");
    }

    /**
     * permite insertar uno o varios empleados en una misma petición
     * @param empleado debe ser una lista
     * @return
     */
    @PostMapping("/employees")
    public ResponseEntity<?> crearEmpleado(@RequestBody String empleado) {
        return ResponseEntity.ok("implementacion de creación de empleado");
    }

    /**
     * actualiza todos o alguno de los campos de un empleado
     * @param id
     * @return
     */
    @PutMapping("/employee/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Integer id) {
        return ResponseEntity.ok("implementacion de actualizacion de empleado");
    }

    /**
     * elimina un empleado por su id
     * @param id
     * @return
     */
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Integer id) {
        return ResponseEntity.ok("implementacion de actualizacion de empleado");
    }

}
