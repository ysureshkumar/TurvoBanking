package com.pramati.turvo.banking.dao;

import java.util.List;

import com.pramati.turvo.banking.model.Customer;

public interface CustomerDAO {
	public Customer save(Customer customer);

	public Customer update(Customer customer);

	public void delete(Customer customer);

	public List<Customer> findAll();

	public Customer getCustomerById(Long customerId);
}
