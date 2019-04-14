package com.pramati.turvo.banking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "counter")
@EntityListeners(AuditingEntityListener.class)
public class Counter {
	
	@Id
	@Column(name = "counterid")
	private Integer counterid;
	
	private String countertype;
	
	public Counter() {
	}

	public Counter(Integer counterid, String countertype) {
		super();
		this.counterid = counterid;
		this.countertype = countertype;
		}

	public Integer getCounterid() {
		return counterid;
	}

	public void setCounterid(Integer counterid) {
		this.counterid = counterid;
	}

	public String getCountertype() {
		return countertype;
	}

	public void setCountertype(String countertype) {
		this.countertype = countertype;
	}
}
