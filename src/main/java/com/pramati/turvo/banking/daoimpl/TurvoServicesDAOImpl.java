package com.pramati.turvo.banking.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pramati.turvo.banking.dao.TurvoServicesDAO;
import com.pramati.turvo.banking.model.TurvoServices;
import com.pramati.turvo.banking.repository.TurvoServicesRepository;

@Service
public class TurvoServicesDAOImpl implements TurvoServicesDAO {

	@Autowired
	TurvoServicesRepository turvoServicesRepository;
	
	public void setServiceRepository(TurvoServicesRepository turvoServicesRepository) {
		this.turvoServicesRepository = turvoServicesRepository;
	}

	public List<TurvoServices> findAll() {
		return turvoServicesRepository.findAll();
	}

	public TurvoServices findOne(Long serviceId) {
		return turvoServicesRepository.findOne(serviceId);
	}
}
