package com.arohau.springrestswagger.service;

import com.arohau.springrestswagger.entity.Employee;
import com.arohau.springrestswagger.exception.EmployeeNotFoundException;
import com.arohau.springrestswagger.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Page<Employee> getPageable(Pageable pageable) {
        Page<Employee> all = employeeRepository.findAll(pageable);
        return all;
    }

    @Override
    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public Employee saveNewEmployee(Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    @Override
    public Employee updateById(Long id, Employee newEmployee) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return employeeRepository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                });
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteByFirstName(String firstName) {
        employeeRepository.deleteByFirstName(firstName);
    }
}
