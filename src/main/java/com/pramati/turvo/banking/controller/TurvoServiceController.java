package com.pramati.turvo.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pramati.turvo.banking.service.CounterService;
import com.pramati.turvo.banking.service.TurvoServicesService;

@RestController
@RequestMapping("/turvo/")
public class TurvoServiceController {

	@Autowired
	TurvoServicesService turvoServicesService;

	@GetMapping("/services")
	public ResponseEntity<? extends Object> getAllTurvoServices() {

		return turvoServicesService.getAllTurvoServices();
	}

	@GetMapping("/services/{serviceId}")
	public ResponseEntity<? extends Object> getCounter(@PathVariable(value = "serviceId") Long serviceId) {
		return turvoServicesService.getTurvoService(serviceId);
	}

}
