package com.arohau.adminweb.data.repository;

import com.arohau.adminweb.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Iterable<Order> findAllByCustomerId(long customerId);
}
