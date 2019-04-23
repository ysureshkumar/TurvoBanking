package com.pramati.turvo.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pramati.turvo.banking.service.CounterService;

@RestController
@RequestMapping("/turvo/")
public class CounterController {

	@Autowired
	CounterService counterService;

	@GetMapping("/counters")
	public ResponseEntity<? extends Object> getAllCounters() {

		return counterService.getAllCounters();
	}

	@GetMapping("/counters/{counterid}")
	public ResponseEntity<? extends Object> getCounter(@PathVariable(value = "counterid") Integer counterid) {
		return counterService.getCounter(counterid);
	}

}
