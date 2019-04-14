package com.pramati.turvo.banking.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pramati.turvo.banking.dao.CounterDAO;
import com.pramati.turvo.banking.model.Counter;
import com.pramati.turvo.banking.service.CounterService;

@Service
public class CounterServiceImpl implements CounterService {

	@Autowired
	CounterDAO counterDAO;

	public CounterDAO getCounterDAO() {
		return counterDAO;
	}

	public void setCounterDAO(CounterDAO counterDAO) {
		this.counterDAO = counterDAO;
	}

	public Counter createCounter(Counter counter) {
		return counterDAO.save(counter);
	}

	public Counter updateCounter(Counter counter) {
		// TODO Auto-generated method stub
		return counterDAO.update(counter);
	}

	public void deleteCounter(Counter counter) {
		counterDAO.delete(counter);
	}

	public List<Counter> getAllCounters() {
		// TODO Auto-generated method stub
		return counterDAO.findAll();
	}

	public Counter getCounterById(Integer CounterId) {
		// TODO Auto-generated method stub
		return counterDAO.findOne(CounterId);
	}

	public Long getCountOfCounters() {
		return counterDAO.getCountOfCounters();
	}
}
