package com.babel.benjamin.empleados_app.controller;

import com.babel.benjamin.empleados_app.config.AppProps;
import com.babel.benjamin.empleados_app.config.Constants;
import com.babel.benjamin.empleados_app.dto.request.EmployeeRequest;
import com.babel.benjamin.empleados_app.model.Employee;
import com.babel.benjamin.empleados_app.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final AppProps appProps;

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
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") @NotNull Integer id) {
        return ResponseEntity.ok(employeeService.getEmployees(id));
    }


    @GetMapping("/employees/search")
    public ResponseEntity<?> getEmployeesByName(
            @RequestParam("name") String name
    ) {
        return ResponseEntity.ok(employeeService.findByName(name));
    }

    /**
     * permite insertar uno o varios empleados en una misma petición
     * @param empleado debe ser una lista
     * @return
     */
    @PostMapping("/employees")
    public ResponseEntity<?> createEmployee(
            @RequestBody
            @NotEmpty(message = Constants.MSG_LIST_EMPLOYEE_EMPTY)
            @Size(max = 10)
            @Valid List<EmployeeRequest> employeesRequests) {
        employeeService.saveEmployees(employeesRequests);

        return ResponseEntity.status(HttpStatus.CREATED).body("datos creados");
    }

    /**
     * actualiza todos o alguno de los campos de un empleado
     * @param id
     * @return
     */
    @PutMapping("/employees/{id}")
    public ResponseEntity<?> updateEmployee(
            @PathVariable("id") Integer id,
            @RequestBody
            @Valid EmployeeRequest employeeRequest) {
        return ResponseEntity.ok(employeeService.updateEmploye(id, employeeRequest));
    }

    /**
     * elimina un empleado por su id
     * @param id
     * @return
     */
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Integer id) {
        employeeService.deleteEmployeeById(id);

        return ResponseEntity.ok().build();
    }

}
