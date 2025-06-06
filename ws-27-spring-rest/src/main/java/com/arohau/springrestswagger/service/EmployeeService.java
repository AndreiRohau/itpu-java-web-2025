package com.arohau.springrestswagger.service;

import com.arohau.springrestswagger.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();
    Employee getById(Long id);
    Employee saveNewEmployee(Employee newEmployee);
    Employee updateById(Long id, Employee newEmployee);
    void deleteById(Long id);
    void deleteByFirstName(String firstName);

    Page<Employee> getPageable(Pageable pageable);
}
