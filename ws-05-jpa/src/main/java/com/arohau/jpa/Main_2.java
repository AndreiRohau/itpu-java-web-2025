package com.arohau.jpa;

import com.arohau.jpa.entity.ActiveEmployee;
import com.arohau.jpa.entity.Company;
import com.arohau.jpa.entity.Employee;
import com.arohau.jpa.entity.Salary;
import com.arohau.jpa.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main_2 {
    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");

        run();
        check();

        entityManagerFactory.close();
    }

    private static void check() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query getAllEmployeesQuery = entityManager.createNativeQuery("SELECT * FROM employees;", Employee.class);
        List<Employee> employees = (List<Employee>) getAllEmployeesQuery.getResultList();
        System.out.println("State: " + employees);
        System.out.println("Expected: Mary Johnson");
    }

    private static void run() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl(entityManager);

        //create a new Employee
        ActiveEmployee employee = new ActiveEmployee();
        employee.setFName("Mary");
        employee.setLName("Doe");
        employee.setYearsExperience(20);

        //set salary
        employee.setSalaries(new ArrayList<>(Arrays.asList(new Salary(54000.00, true))));

        //set company
        employee.setCompanies(new ArrayList<>(Arrays.asList(new Company("MyCompany", "NA"))));

        Employee employee2 = new Employee();
        employee2.setFName("James");
        employee2.setLName("Doe");
        employee2.setYearsExperience(5);

        //save Employees
        employeeRepository.save(employee);
        employeeRepository.save(employee2);

        //update Employee
        Optional<Employee> retrievedEmployee = employeeRepository.getEmployeeById(1L);
        retrievedEmployee.get().setLName("Johnson");
        employeeRepository.save(retrievedEmployee.get());

        //delete Employee
        employeeRepository.deleteEmployee(employee2);

        entityManager.close();
    }
}