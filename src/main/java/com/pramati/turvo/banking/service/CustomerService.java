package com.pramati.turvo.banking.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pramati.turvo.banking.model.Customer;

public interface CustomerService {
	public Customer createCustomer(Customer customer);

	public  ResponseEntity<? extends Object> updateCustomer(Long customerId, Customer customer);

	public  ResponseEntity<? extends Object> deleteCustomer(Long customerId);

	public  ResponseEntity<? extends Object> getAllCustomers();

	public  ResponseEntity<? extends Object> getCustomer(Long customerId);
	
	public  Customer getCustomerById(Long customerId);
	
}
