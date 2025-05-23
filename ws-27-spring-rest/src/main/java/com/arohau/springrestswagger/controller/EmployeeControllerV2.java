package com.arohau.springrestswagger.controller;

import com.arohau.springrestswagger.entity.Employee;
import com.arohau.springrestswagger.hateoas.EmployeeModelAssembler;
import com.arohau.springrestswagger.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("employees/v2")
public class EmployeeControllerV2 {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeModelAssembler employeeModelAssembler;

    @GetMapping("/{id}")
    public EntityModel<Employee> one(@PathVariable Long id) {

        Employee employee = employeeService.getById(id);

        return EntityModel.of(employee,
                linkTo(methodOn(EmployeeControllerV2.class).one(id)).withSelfRel(),
                linkTo(methodOn(EmployeeControllerV2.class).all(PageRequest.of(1, 1))).withRel("employees"));
    }

    @GetMapping // http://localhost:8080/employees/v2?page=0&size=2
    public Object all(Pageable pageable) {
        // getting a page of Items
        Page<Employee> page = employeeService.getPageable(pageable);

        // Adding links to Items by transforming them to EntityModels
        List<EntityModel<Employee>> employeeModels = page.getContent().stream()
                .map(employee -> EntityModel.of(employee,
                        linkTo(methodOn(EmployeeControllerV2.class).one(employee.getId())).withSelfRel(),
                        linkTo(methodOn(EmployeeControllerV2.class).all(page.getPageable())).withRel("employees")))
                .collect(Collectors.toList());

        // preparing new Page Response
        Page<EntityModel<Employee>> pageResponse = new PageImpl<>(employeeModels, pageable, page.getTotalElements());

        return EntityModel.of(pageResponse,
                linkTo(methodOn(EmployeeControllerV2.class).all(pageable)).withSelfRel());
    }

}
