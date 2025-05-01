package com.arohau.ldapbasedauth.web;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderModel {
    private long orderId;
    private String customer;
    private long customerId;
    private String orderDetails;
}
