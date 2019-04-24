package com.pramati.turvo.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pramati.turvo.banking.model.Counter;
import com.pramati.turvo.banking.model.TurvoServices;
import com.pramati.turvo.banking.model.Token;

public interface TurvoServicesRepository extends JpaRepository<TurvoServices, Long> {

}
