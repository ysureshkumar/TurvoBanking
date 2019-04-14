package com.pramati.turvo.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pramati.turvo.banking.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
