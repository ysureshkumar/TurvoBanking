package com.pramati.turvo.banking.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pramati.turvo.banking.dao.CounterDAO;
import com.pramati.turvo.banking.model.Counter;
import com.pramati.turvo.banking.service.CounterService;
import com.pramati.turvo.banking.service.TokenService;

@Service
public class CounterServiceImpl implements CounterService {

	@Autowired
	CounterDAO counterDAO;
	
	@Autowired
	TokenService tokenService;

	public CounterDAO getCounterDAO() {
		return counterDAO;
	}

	public void setCounterDAO(CounterDAO counterDAO) {
		this.counterDAO = counterDAO;
	}

	public ResponseEntity<? extends Object> getAllCounters() {
		// TODO Auto-generated method stub
		return tokenService.getAllCounters();
	}

	public Counter  getCounterById(Integer counterId) {
		// TODO Auto-generated method stub
		return counterDAO.findOne(counterId);
	}

	public ResponseEntity<? extends Object> getCounter(Integer counterId) {
		// TODO Auto-generated method stub
		return tokenService.getCounterById(counterId);
	}

	public Long getCountOfCounters() {
		return counterDAO.getCountOfCounters();
	}
}
