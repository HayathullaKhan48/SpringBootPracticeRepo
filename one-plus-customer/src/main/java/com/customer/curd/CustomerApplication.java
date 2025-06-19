package com.customer.curd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerApplication {

	/**
	 * Main method that serves as the entry point for the spring boot application
	 * to Running the Spring boot application.
	 */
		public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
		// Printing a message to the console indicating the application has started successfully.
		System.out.println("Customer is successfully got the Pass Key");
	}

}
