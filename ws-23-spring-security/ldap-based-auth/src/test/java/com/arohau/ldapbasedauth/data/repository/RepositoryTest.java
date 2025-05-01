package com.arohau.ldapbasedauth.data.repository;

import com.arohau.ldapbasedauth.data.model.Customer;
import com.arohau.ldapbasedauth.data.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testGetAllOrders(){
        List<Order> orders = orderRepository.findAll();
        assertEquals(3, orders.size());
    }

    @Test
    public void testGetAllCustomers(){
        List<Customer> customers = customerRepository.findAll();
        assertEquals(7, customers.size());
    }
}
