package com.customer.curd.controller;

import com.customer.curd.request.CustomerRequest;
import com.customer.curd.response.CustomerResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Customer Controller class handles all customer-related operations.
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    Collection<CustomerResponse> customers = new ArrayList<>();

    /**
     * Sample GET method to return hardcoded data (not connected to real list)
     *
     * @return list
     */
    @GetMapping("/Service-data")
    Collection<CustomerResponse> getCustomerData(){
        return List.of(
                new CustomerResponse(1001, "Hayathulla", 6304474604L,"OnePlus 9R 8Gb/128gb", "Green Line problem",2324.32f),
                new CustomerResponse(1002, "Sufiyan",8985415771L,"OnePlus 10 12/256gb", "Battery problem",2345.4f),
                new CustomerResponse(1003,"Arzoo",9182299568L,"OnePlus 11R 12/256gb","Over Heating",7873.43f),
                new CustomerResponse(1004,"Saira khanam",9515421910L,"OnePlus Fold 16/1TB","Camera issue", 9076.43f)
        );
    }

    /**
     * Create a customer and Store in memory
     *
     * @param request customer request object
     * @return string
     */
   @PostMapping("/create-customer")
    public String createCustomer(@RequestBody CustomerResponse request){
        customers.add(request);
       System.out.println(customers);
       return "Customer is Successfully Registered...";
   }

    /**
     * Create a Customer and Store in memory
     *
     * @return customers
     */
   @GetMapping("/get/all/customers")
    public Collection<CustomerResponse> getAllCustomers() {
        return customers;
    }

    /**
     * Get a Customer by Pass key
     *
     * @param customerPasskey getAllCustomer
     * @return customers
     */
    @GetMapping("/get/customer-by-key/{customerPassKey}")
    public CustomerResponse getAllCustomer(@PathVariable int customerPasskey) {
        return customers.stream()
                .filter(customers -> customers.getCustomerPasskey() == customerPasskey)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer with Pass Key " + customerPasskey + " not found."));
    }

    /**
     * Delete a Customer by Pass Key
     *
     * @param customerPassKey String
     * @return String
     */
    @DeleteMapping("/delete/customer-by-key/{customerPassKey}")
    public String deleteCustomer(@PathVariable int customerPassKey) {
        boolean removed = customers.removeIf(customer -> customer.getCustomerPasskey() == customerPassKey);
        return removed
                ? "Customer with Pass Key " + customerPassKey + " deleted successfully."
                : "Customer with Pass Key " + customerPassKey + " not found.";
    }

    /**
     * Full Update using PUT
     *
     * @param request CustomerRequest
     * @return String
     */
    @PutMapping("/update")
    public String updateCustomer(@RequestBody CustomerRequest request) {
        try {
            for (CustomerResponse customer : customers) {
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
           // e.printStackTrace();
            return "Error occurred: " + e.getMessage();
        }
    }

    /**
     * partial Update using PATCH
     *
     * @param customerPasskey the unique ID to identify the customer
     * @param request the data containing fields to be updated
     * @return String
     */
    @PatchMapping("/update/field/{customerPasskey}")
    public String patchCustomer(@PathVariable int customerPasskey, @RequestBody CustomerRequest request) {
        for (CustomerResponse customer : customers) {
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
