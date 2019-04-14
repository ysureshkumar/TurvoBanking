package com.pramati.turvo.banking.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pramati.turvo.banking.dao.CustomerDAO;
import com.pramati.turvo.banking.model.Customer;
import com.pramati.turvo.banking.repository.CustomerRepository;

@Service
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	CustomerRepository customerRepository;
	
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public Customer save(Customer customer) {
		return customerRepository.saveAndFlush(customer);
	}

	public Customer update(Customer customer) {
		return customerRepository.saveAndFlush(customer);
	}

	public void delete(Customer customer) {
		customerRepository.delete(customer);
	}

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	public Customer getCustomerById(Long customerId) {
		return customerRepository.findOne(customerId);
	}
}
