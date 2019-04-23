package com.pramati.turvo.banking.dao;

import java.util.List;

import com.pramati.turvo.banking.model.Counter;

public interface CounterDAO {
	public List<Counter> findAll();
	public Counter findOne(Integer counterid);
	public Long getCountOfCounters();
	 
}
