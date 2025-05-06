package com.arohau.springrest.controller;

import com.arohau.springrest.entity.Employee;
import com.arohau.springrest.exception.EmployeeNotFoundException;
import com.arohau.springrest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping
    public List<Employee> all() {
        return employeeService.getAll();
    }
    // end::get-aggregate-root[]

    // Single item
    @GetMapping("/{id}")
    public Employee one(@PathVariable Long id) throws EmployeeNotFoundException {
        return employeeService.getById(id);
    }

    @PostMapping
    public Employee newEmployee(@RequestBody Employee newEmployee) {
        return employeeService.saveNewEmployee(newEmployee);
    }

    @PutMapping("/{id}")
    public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) throws EmployeeNotFoundException {
        return employeeService.updateById(id, newEmployee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
    }

    @DeleteMapping("/name/{name}")
    public void deleteEmployee(@PathVariable String name) {
        employeeService.deleteByName(name);
    }
}
