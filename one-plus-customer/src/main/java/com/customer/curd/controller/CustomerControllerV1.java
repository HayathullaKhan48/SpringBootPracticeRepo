package com.customer.curd.controller;

import com.customer.curd.request.Customer;
import com.customer.curd.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Customer Controller class handles all customer-related operations.
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerControllerV1 {

    @Autowired
    private CustomerService customerService;

    /**
     * Create a customer and Store in memory
     *
     * @param request customer request object
     * @return string
     */
   @PostMapping("/create-customer")
    public String createCustomer(@RequestBody Customer request){
        return customerService.createCustomer(request);
   }

    /**
     * Create a Customer and Store in memory
     *
     * @return customers
     */
   @GetMapping("/get/all/customers")
    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    /**
     * Get a Customer by Pass key
     *
     * @param customerPasskey getAllCustomer
     * @return customers
     */
    @GetMapping("/get/customer-by-key/{customerPassKey}")
    public Customer getCustomer(@PathVariable int customerPasskey) {
        return customerService.getCustomer(customerPasskey);
    }

    /**
     * Delete a Customer by Pass Key
     *
     * @param customerPassKey String
     * @return String
     */
    @DeleteMapping("/delete/customer-by-key/{customerPassKey}")
    public String deleteCustomer(@PathVariable int customerPassKey) {
        return customerService.deleteCustomer(customerPassKey);
    }

    /**
     * Full Update using PUT
     *
     * @param request CustomerRequest
     * @return String
     */
    @PutMapping("/update")
    public String updateCustomer(@RequestBody Customer request) {
        return customerService.updateCustomer(request);
    }

    /**
     * partial Update using PATCH
     *
     * @param customerPasskey the unique ID to identify the customer
     * @param request the data containing fields to be updated
     * @return String
     */
    @PatchMapping("/update/field/{customerPasskey}")
    public String patchCustomer(@PathVariable int customerPasskey, @RequestBody Customer request) {
        return customerService.patchCustomer(customerPasskey, request);
    }
}
