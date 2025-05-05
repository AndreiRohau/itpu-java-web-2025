package com.arohau.springresthateoas.repository;

import com.arohau.springresthateoas.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    void deleteByFirstName(String firstName);
}
