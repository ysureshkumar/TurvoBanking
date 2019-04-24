package com.pramati.turvo.banking.dao;

import java.util.List;

import com.pramati.turvo.banking.model.TurvoServices;

public interface TurvoServicesDAO {
	public List<TurvoServices> findAll();
	public TurvoServices findOne(Long serviceId);	 
}
