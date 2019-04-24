package com.pramati.turvo.banking.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pramati.turvo.banking.dao.CounterDAO;
import com.pramati.turvo.banking.dao.TurvoServicesDAO;
import com.pramati.turvo.banking.errors.TurvoException;
import com.pramati.turvo.banking.model.Counter;
import com.pramati.turvo.banking.model.TurvoServices;
import com.pramati.turvo.banking.service.CounterService;
import com.pramati.turvo.banking.service.TokenService;
import com.pramati.turvo.banking.service.TurvoServicesService;

@Service
public class TurvoServicesServiceImpl implements TurvoServicesService {

	@Autowired
	TurvoServicesDAO turvoServicesDAO;
	

	public TurvoServicesDAO getTurvoServicesDAO() {
		return turvoServicesDAO;
	}

	public void setTurvoServicesDAO(TurvoServicesDAO turvoServicesDAO) {
		this.turvoServicesDAO = turvoServicesDAO;
	}

	public ResponseEntity<? extends Object> getAllTurvoServices() {

		List<TurvoServices> listOfTurvoServices = turvoServicesDAO.findAll();
		if (listOfTurvoServices == null) {
			TurvoException turvoException = new TurvoException();
			turvoException.setStatus(HttpStatus.OK.value());
			turvoException.setMessage("No Services Are Available");
			turvoException.setError(null);
			turvoException.setException(null);
			return ResponseEntity.ok().body(turvoException);
		}
		
		return ResponseEntity.ok().body(listOfTurvoServices);
	}

	public TurvoServices  getTurvoServiceById(Long serviceId) {
		return turvoServicesDAO.findOne(serviceId);
	}

	public ResponseEntity<? extends Object> getTurvoService(Long serviceId) {
		
		TurvoServices turvoService = turvoServicesDAO.findOne(serviceId);
		if (turvoService == null) {
			TurvoException turvoException = new TurvoException();
			turvoException.setStatus(HttpStatus.OK.value());
			turvoException.setMessage("Invalid Service Id: "+serviceId);
			turvoException.setError(null);
			turvoException.setException(null);
			return ResponseEntity.ok().body(turvoException);
		}
		
		return ResponseEntity.ok().body(turvoService);
	}
}
