package com.pramati.turvo.banking.service;

import org.springframework.http.ResponseEntity;

import com.pramati.turvo.banking.model.Counter;
import com.pramati.turvo.banking.model.TurvoServices;

public interface TurvoServicesService {
	public ResponseEntity<? extends Object> getAllTurvoServices();

	public ResponseEntity<? extends Object> getTurvoService(Long counterId);

	public TurvoServices getTurvoServiceById(Long counterId);

}
