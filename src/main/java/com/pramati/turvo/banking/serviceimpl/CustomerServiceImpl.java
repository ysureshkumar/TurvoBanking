package com.pramati.turvo.banking.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pramati.turvo.banking.dao.CustomerDAO;
import com.pramati.turvo.banking.errors.TurvoException;
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

	public ResponseEntity<? extends Object> updateCustomer(Long customerId, Customer customer) {

		Customer customerToUpdate = customerDAO.getCustomerById(customerId);

		if (customerToUpdate == null) {
			TurvoException turvoException = new TurvoException();
			turvoException.setStatus(HttpStatus.BAD_REQUEST.value());
			turvoException.setMessage("Invalid Customer Id: " + customerId);
			turvoException.setError(HttpStatus.BAD_REQUEST);
			turvoException.setException(turvoException.getClass().toString());
			return ResponseEntity.ok().body(turvoException);
		}
		customerToUpdate.setName(customer.getName());
		customerToUpdate.setPhno(customer.getPhno());
		customerToUpdate.setAddress(customer.getAddress());
		customerToUpdate.setServicetype(customer.getServicetype());

		return ResponseEntity.ok().body(customerDAO.update(customerToUpdate).toString());
	}

	public void deleteCustomer(Customer customer) {

		customerDAO.delete(customer);
	}

	public ResponseEntity<? extends Object> deleteCustomer(Long customerId) {
		Customer customer = customerDAO.getCustomerById(customerId);

		if (customer == null) {
			TurvoException turvoException = new TurvoException();
			turvoException.setStatus(HttpStatus.BAD_REQUEST.value());
			turvoException.setMessage("Invalid Customer Id: " + customerId);
			turvoException.setError(HttpStatus.BAD_REQUEST);
			turvoException.setException(turvoException.getClass().toString());
			return ResponseEntity.ok().body(turvoException);
		}
		customerDAO.delete(customer);
		TurvoException turvoException = new TurvoException();
		turvoException.setStatus(HttpStatus.OK.value());
		turvoException.setMessage("Customer Id " + customerId + "Deleted");
		turvoException.setError(null);
		turvoException.setException(null);
		return ResponseEntity.ok().body(turvoException);
	}

	public ResponseEntity<? extends Object> getAllCustomers() {
		List<Customer> lisOfCustomers = new ArrayList<Customer>();
		lisOfCustomers = customerDAO.findAll();
		if (lisOfCustomers.size() == 0) {
			TurvoException turvoException = new TurvoException();
			turvoException.setStatus(HttpStatus.OK.value());
			turvoException.setMessage("No Customers Available");
			turvoException.setError(null);
			turvoException.setException(null);
		}
		return ResponseEntity.ok().body(lisOfCustomers);
	}

	public ResponseEntity<? extends Object> getCustomer(Long customerId) {
		Customer customer = customerDAO.getCustomerById(customerId);

		if (customer == null) {
			TurvoException turvoException = new TurvoException();
			turvoException.setStatus(HttpStatus.BAD_REQUEST.value());
			turvoException.setMessage("Invalid Customer Id: " + customerId);
			turvoException.setError(HttpStatus.BAD_REQUEST);
			turvoException.setException(turvoException.getClass().toString());
			return ResponseEntity.ok().body(turvoException);
		}
		return ResponseEntity.ok().body(customer);
	}

	public Customer getCustomerById(Long customerId) {
		return customerDAO.getCustomerById(customerId);
	}
}
