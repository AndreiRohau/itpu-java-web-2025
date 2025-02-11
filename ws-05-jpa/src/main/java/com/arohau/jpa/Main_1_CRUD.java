package com.arohau.jpa;

import com.arohau.jpa.entity.Company;
import jakarta.persistence.*;

import java.util.List;

public class Main_1_CRUD {

    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");

        saveCompany();
        getCompany();
        updateCompany();
        getCompany();
        deleteCompany();
        getCompany();

        entityManagerFactory.close();
    }

    // create
    private static void saveCompany() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Company company = new Company();
        company.setName("adidas");
        entityManager.persist(company);
        transaction.commit();

        System.out.println("Result save: " + company);
        entityManager.close();
    }

    // read
    private static void getCompany() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
//        Company company = entityManager.find(Company.class, 1);
        List<Company> companies = (List<Company>) entityManager.createNativeQuery("SELECT * FROM companies;", Company.class).getResultList();
        transaction.commit();

//        System.out.println("Result get: " + company);
        System.out.println("Result: get all" + companies);
        entityManager.close();
    }

    // update
    private static void updateCompany() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Company company = entityManager.find(Company.class, 1);
        company.setName("PUMA");
        transaction.commit();

        System.out.println("Result Update: " + company);
        entityManager.close();
    }

    // delete
    private static void deleteCompany() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Company company = entityManager.find(Company.class, 1);
        entityManager.remove(company);
        transaction.commit();

        System.out.println("Result: delete" + company);
        entityManager.close();
    }
}
