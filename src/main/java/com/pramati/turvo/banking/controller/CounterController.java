package com.pramati.turvo.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pramati.turvo.banking.model.CounterQueue;
import com.pramati.turvo.banking.service.TokenService;

@RestController
@RequestMapping("/turvo/counters")
public class CounterController {

	@Autowired
	TokenService tokenService;
	
	@GetMapping("/getcounter/{counterid}")
	public ResponseEntity<CounterQueue> getCounter(@PathVariable(value = "counterid") Integer counterid) {

		CounterQueue counterQueue = tokenService.getCounterById(counterid);
		if (counterQueue == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(counterQueue);
	}

	@GetMapping("/getallcounters")
	public List<CounterQueue> getAllCounters() {

		List<CounterQueue> listOfAllCounters = tokenService.getAllCounters();
		if (listOfAllCounters.size() == 0) {
			return null;
		}
		return listOfAllCounters;
	}
}
