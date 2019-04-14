package com.pramati.turvo.banking.dao;

import java.util.List;

import com.pramati.turvo.banking.model.Counter;

public interface CounterDAO {
	public Counter save(Counter counter);

	public Counter update(Counter counter);

	public void delete(Counter counter);

	public List<Counter> findAll();

	public Counter findOne(Integer counterid);
	public Long getCountOfCounters();
	 
}
