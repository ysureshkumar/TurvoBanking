package com.pramati.turvo.banking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pramati.turvo.banking.model.Customer;
import com.pramati.turvo.banking.service.CustomerService;

@RestController
@RequestMapping("/turvo/customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;
 
	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping("/createcustomer")
	public Customer createCustomer(@Valid @RequestBody Customer customer){
		return customerService.createCustomer(customer);
	}

	@PutMapping("/updatecustomer/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "customerId") Long customerId,
			@Valid @RequestBody Customer customer) {

		Customer customerToUpdate = customerService.updateCustomer(customerId,customer);

		if (customerToUpdate == null) {
			return ResponseEntity.notFound().build();
		}
		
		return  ResponseEntity.ok().body(customerToUpdate);
	}

	@GetMapping("/getallcustomers")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/getcustomer/{customerId}")
	public ResponseEntity<Customer> createCustomer(@PathVariable(value = "customerId") Long customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		if (customer == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(customer);
	}
}
