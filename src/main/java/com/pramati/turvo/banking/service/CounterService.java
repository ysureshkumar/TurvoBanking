package com.pramati.turvo.banking.service;

import org.springframework.http.ResponseEntity;

import com.pramati.turvo.banking.model.Counter;

public interface CounterService {
	public ResponseEntity<? extends Object>  getAllCounters();

	public ResponseEntity<? extends Object>  getCounter(Integer counterId);
	
	public Counter  getCounterById(Integer counterId);
		
	public Long getCountOfCounters();
}
