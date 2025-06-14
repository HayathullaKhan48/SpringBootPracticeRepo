package com.customer.curd.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Customer Response class represents the response object for customer-related operation.
 * it contains all the necessary information about a customer that will be returned
 * to the client after performing operation such as creating, updating or retrieving customer data.
 */
@Setter
@Getter
public class CustomerResponse {
    private int customerPasskey;
    private String customerName;
    private long customerPhoneNumber;
    private String model;
    private String customerProblem;
    private float customerBillAmount;

    public CustomerResponse(int customerPasskey, String customerName, long customerPhoneNumber, String model, String customerProblem, float customerBillAmount) {
        this.customerPasskey = customerPasskey;
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.model = model;
        this.customerProblem = customerProblem;
        this.customerBillAmount = customerBillAmount;

    }
}
