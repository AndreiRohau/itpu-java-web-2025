package com.arohau.jpa;

import com.arohau.jpa.entity.ActiveEmployee;
import com.arohau.jpa.entity.Company;
import com.arohau.jpa.entity.EmployeeProfile;
import com.arohau.jpa.entity.Salary;
import com.arohau.jpa.repository.EmployeeRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class Main_3 {

    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        run();
        entityManagerFactory.close();
    }

    private static void run() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl(entityManager);

        ActiveEmployee employee = new ActiveEmployee();
        employee.setFName("Mary");
        employee.setLName("Johnson");
        employee.setYearsExperience(20);

        ActiveEmployee employee2 = new ActiveEmployee();
        employee2.setFName("John");
        employee2.setLName("Doe");
        employee2.setYearsExperience(5);

        //set employment history
        employee.setCompanies(generateCompanies());
        employee2.setCompanies(generateCompanies());

        //create an EmployeeProfile and associate it to an Employee
        employee.setProfile(new EmployeeProfile("userName", "password!", "email@email.com", employee, "Software Engineer"));
        employee2.setProfile(new EmployeeProfile("jDoe", "password234", "johndoe@email.com", employee2, "Project Manager"));

        //set salaries
        employee.setSalaries(generateSalaries());
        employee2.setSalaries(generateSalaries());

        //save Employee
        employeeRepository.save(employee);
        employeeRepository.save(employee2);

        entityManager.close();
    }

    private static List<Company> generateCompanies() {
        Company company1 = new Company("Google", "USA");
        Company company2 = new Company("Amazon", "USA");

        List<Company> companies = new ArrayList<>();
        companies.add(company1);
        companies.add(company2);

        return companies;
    }

    private static List<Salary> generateSalaries() {
        //create the Salaries and associate to Employee
        Salary currentSalary = new Salary(34000.00, true);
        Salary historicalSalary1 = new Salary(10000.00, false);
        Salary historicalSalary2 = new Salary(5000.00, false);

        List<Salary> salaries = new ArrayList<>();
        salaries.add(currentSalary);
        salaries.add(historicalSalary1);
        salaries.add(historicalSalary2);

        return salaries;
    }

}