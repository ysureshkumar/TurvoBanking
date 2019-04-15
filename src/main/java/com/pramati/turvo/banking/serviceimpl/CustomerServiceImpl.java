package com.pramati.turvo.banking.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pramati.turvo.banking.dao.CustomerDAO;
import com.pramati.turvo.banking.model.Customer;
import com.pramati.turvo.banking.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDAO customerDAO;
	
	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	public Customer createCustomer(Customer customer) {
		
		return customerDAO.save(customer);
	}

	public Customer updateCustomer(Long customerId, Customer customer) {

		Customer customerToUpdate = customerDAO.getCustomerById(customerId);

		if (customerToUpdate == null) {
			return null;
		}
		customerToUpdate.setName(customer.getName());
		customerToUpdate.setPhno(customer.getPhno());
		customerToUpdate.setAddress(customer.getAddress());
		customerToUpdate.setServicetype(customer.getServicetype());
		
		return  customerDAO.update(customerToUpdate);
	}

	public void deleteCustomer(Customer customer) {
		customerDAO.delete(customer);
	}

	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerDAO.findAll();
	}

	public Customer getCustomerById(Long customerId) {
		// TODO Auto-generated method stub
		return customerDAO.getCustomerById(customerId);
	}
}
