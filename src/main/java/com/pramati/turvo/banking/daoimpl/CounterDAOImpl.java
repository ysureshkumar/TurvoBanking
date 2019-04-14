package com.pramati.turvo.banking.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.pramati.turvo.banking.dao.CounterDAO;
import com.pramati.turvo.banking.model.Counter;
import com.pramati.turvo.banking.repository.CounterRepository;

@Service
public class CounterDAOImpl implements CounterDAO {

	@Autowired
	CounterRepository counterRepository;
	
	public void setcounterRepository(CounterRepository counterRepository) {
		this.counterRepository = counterRepository;
	}

	public Counter save(Counter counter) {
		return counterRepository.saveAndFlush(counter);
	}

	public Counter update(Counter counter) {
		return counterRepository.saveAndFlush(counter);
	}

	public void delete(Counter counter) {
		counterRepository.delete(counter);
	}

	public List<Counter> findAll() {
		return counterRepository.findAll();
	}

	public Counter findOne(Integer counterId) {
		return counterRepository.findOne(counterId);
	}

	public Long getCountOfCounters() {
	return counterRepository.getCountOfCounters();
	}
}
