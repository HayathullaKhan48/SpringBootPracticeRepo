package com.customer.curd.request;

import lombok.Getter;
import lombok.Setter;
/**
 * CustomerRequest class represents the request object for customer-related operations.
 * It is used to capture the data sent by the client when creating or updating a customer.
 */
@Setter
@Getter
public class CustomerRequest {
    private int customerPasskey;
    private String customerName;
    private long customerPhoneNumber;
    private String model;
    private String customerProblem;
    private float customerBillAmount;
}
