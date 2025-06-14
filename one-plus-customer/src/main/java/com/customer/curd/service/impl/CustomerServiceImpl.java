package com.customer.curd.service.impl;

import com.customer.curd.request.Customer;
import com.customer.curd.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomerServiceImpl implements CustomerService {

    Collection<Customer> customers = new ArrayList<>();

    @Override
    public String createCustomer(Customer request) {
        customers.add(request);
        return "Customer is Successfully Registered...";
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        return customers;
    }

    @Override
    public Customer getCustomer(int customerPasskey) {
        return customers.stream()
                .filter(customers -> customers.getCustomerPasskey() == customerPasskey)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer with Pass Key " + customerPasskey + " not found."));
    }

    @Override
    public String deleteCustomer(int customerPassKey) {
        boolean removed = customers.removeIf(customer -> customer.getCustomerPasskey() == customerPassKey);
        return removed
                ? "Customer with Pass Key " + customerPassKey + " deleted successfully."
                : "Customer with Pass Key " + customerPassKey + " not found.";
    }

    @Override
    public String updateCustomer(Customer request) {
        try {
            for (Customer customer : customers) {
                if (customer.getCustomerPasskey() == request.getCustomerPasskey()) {
                    customer.setCustomerName(request.getCustomerName());
                    customer.setCustomerPhoneNumber(request.getCustomerPhoneNumber());
                    customer.setModel(request.getModel());
                    customer.setCustomerProblem(request.getCustomerProblem());
                    customer.setCustomerBillAmount(request.getCustomerBillAmount());
                    return "Customer with Pass Key " + request.getCustomerPasskey() + " was updated successfully.";
                }
            }
            return "Customer with Pass Key " + request.getCustomerPasskey() + " not found.";
        } catch (Exception e) {
            return "Error occurred: " + e.getMessage();
        }
    }

    @Override
    public String patchCustomer(int customerPasskey, Customer request) {
        for (Customer customer : customers) {
            if (customer.getCustomerPasskey() == customerPasskey) {
                // Update fields only if they are not null or not zero/empty
                if (request.getCustomerName() != null) customer.setCustomerName(request.getCustomerName());
                if (request.getCustomerPhoneNumber() != 0) customer.setCustomerPhoneNumber(request.getCustomerPhoneNumber());
                if (request.getModel() != null) customer.setModel(request.getModel());
                if (request.getCustomerProblem() != null) customer.setCustomerProblem(request.getCustomerProblem());
                if (request.getCustomerBillAmount() != 0.0f) customer.setCustomerBillAmount(request.getCustomerBillAmount());
                return "Customer with Pass Key " + customerPasskey + " was partially updated successfully.";
            }
        }
        return "Customer with Pass Key " + customerPasskey + " not found.";
    }
}
