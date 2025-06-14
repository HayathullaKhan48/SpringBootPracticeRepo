package com.customer.curd.service;

import com.customer.curd.request.Customer;

import java.util.Collection;

public interface CustomerService {

    String createCustomer(Customer request);
    Collection<Customer> getAllCustomers();
    Customer getCustomer(int customerPasskey);
    String deleteCustomer(int customerPassKey);
    String updateCustomer(Customer request);
    String patchCustomer(int customerPasskey, Customer request);

}
