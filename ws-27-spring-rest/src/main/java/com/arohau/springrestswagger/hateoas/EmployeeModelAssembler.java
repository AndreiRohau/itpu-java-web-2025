package com.arohau.springrestswagger.hateoas;

import com.arohau.springrestswagger.controller.EmployeeControllerV3;
import com.arohau.springrestswagger.entity.Employee;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

    @Override
    public EntityModel<Employee> toModel(Employee employee) {

        return EntityModel.of(employee,
                linkTo(methodOn(EmployeeControllerV3.class).one(employee.getId())).withSelfRel(),
                linkTo(methodOn(EmployeeControllerV3.class).all()).withRel("employees"));
    }
}
