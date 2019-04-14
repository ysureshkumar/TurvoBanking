package com.pramati.turvo.banking.service;

import java.util.List;

import com.pramati.turvo.banking.model.Counter;

public interface CounterService {
	public Counter createCounter(Counter counter);

	public Counter updateCounter(Counter counter);

	public void deleteCounter(Counter counter);

	public List<Counter> getAllCounters();

	public Counter getCounterById(Integer counterId);
	public Long getCountOfCounters();
}
