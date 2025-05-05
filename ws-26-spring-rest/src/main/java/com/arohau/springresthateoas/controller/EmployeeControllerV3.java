package com.arohau.springresthateoas.controller;

import com.arohau.springresthateoas.entity.Employee;
import com.arohau.springresthateoas.hateoas.EmployeeModelAssembler;
import com.arohau.springresthateoas.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("employees/v3")
public class EmployeeControllerV3 {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeModelAssembler employeeModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Employee>> all() {

        List<EntityModel<Employee>> employees = employeeService.getAll().stream()
                .map(employeeModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeControllerV3.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Employee> one(@PathVariable Long id) {

        Employee employee = employeeService.getById(id);

        return employeeModelAssembler.toModel(employee);
    }

    @PostMapping
    public ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) {

        EntityModel<Employee> entityModel = employeeModelAssembler.toModel(employeeService.saveNewEmployee(newEmployee));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) // added location of recently created resource in header
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        Employee updatedEmployee = employeeService.updateById(id, newEmployee);

        EntityModel<Employee> entityModel = employeeModelAssembler.toModel(updatedEmployee);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) // added location of recently created resource in header
                .body(entityModel);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {

        employeeService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
