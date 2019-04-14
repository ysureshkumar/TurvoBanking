package com.pramati.turvo.banking.model;

import java.util.Queue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

public class CounterQueue {
	
	private Integer counterId;
	
	private String counterType;
		
	private Queue<Long> counterQ;

	public Integer getCounterId() {
		return counterId;
	}

	public void setCounterId(Integer counterId) {
		this.counterId = counterId;
	}

	public String getCounterType() {
		return counterType;
	}

	public void setCounterType(String counterType) {
		this.counterType = counterType;
	}

	public Queue<Long> getCounterQ() {
		return counterQ;
	}

	public void setCounterQ(Queue<Long> counterQ) {
		this.counterQ = counterQ;
	}
}
