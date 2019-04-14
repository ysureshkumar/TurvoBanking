package com.pramati.turvo.banking.service;

import java.util.List;

import com.pramati.turvo.banking.model.Customer;

public interface CustomerService {
	public Customer createCustomer(Customer customer);

	public Customer updateCustomer(Long customerId, Customer customer);

	public void deleteCustomer(Customer customer);

	public List<Customer> getAllCustomers();

	public Customer getCustomerById(Long customerId);
}
