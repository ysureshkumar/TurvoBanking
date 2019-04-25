package com.pramati.turvo.banking.model;

import java.util.Queue;

public class CounterQueue {

	private Counter counterEntity;

	private Queue<Long> counterQ;


	public Counter getCounterEntity() {
		return counterEntity;
	}

	public void setCounterEntity(Counter counterEntity) {
		this.counterEntity = counterEntity;
	}

	public Queue<Long> getCounterQ() {
		return counterQ;
	}

	public void setCounterQ(Queue<Long> counterQ) {
		this.counterQ = counterQ;
	}
}
