package com.pramati.turvo.banking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pramati.turvo.banking.model.Customer;
import com.pramati.turvo.banking.service.CustomerService;

@RestController
@RequestMapping("/turvo/")
public class CustomerController {

	@Autowired
	CustomerService customerService;
 
	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping("/customers")
	public Customer createCustomer(@Valid @RequestBody Customer customer){
		return customerService.createCustomer(customer);
	}

	@PutMapping("/customers/{customerId}")
	public ResponseEntity<? extends Object> updateCustomer(@PathVariable(value = "customerId") Long customerId,
			@Valid @RequestBody Customer customer) {

		return  customerService.updateCustomer(customerId,customer);
	}

	@GetMapping("/customers")
	public ResponseEntity<? extends Object> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/customers/{customerId}")
	public ResponseEntity<? extends Object> getCustomer(@PathVariable(value = "customerId") Long customerId) {
		return  customerService.getCustomer(customerId);
	}
	
	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<? extends Object> deleteCustomer(@PathVariable(value = "customerId") Long customerId) {
		return  customerService.deleteCustomer(customerId);
	}
	
}
