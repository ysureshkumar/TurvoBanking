package com.pramati.turvo.banking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "token")
@EntityListeners(AuditingEntityListener.class)
public class Token {
	@Id
	@Column(name = "tokenid")
	private Long tokenid;

	private Long customerid;

	private Integer counterid;

	private Long serviceid;

	private String status;

	@ManyToOne(optional=false)
	@JoinColumn(name="customerid",referencedColumnName="customerid", insertable=false, updatable=false)
	@JsonIgnore
	Customer customerEntity;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="counterid",referencedColumnName="counterid", insertable=false, updatable=false)
	@JsonIgnore
	Counter counterEntity;

	@ManyToOne(optional=false)
	@JoinColumn(name="serviceid",referencedColumnName="serviceid", insertable=false, updatable=false)
	@JsonIgnore
	TurvoServices serviceEntity;
	
	public Token() {
	}

	public Token(Long tokenid, Long customerid, Integer counterid, Long serviceid, String status) {
		super();
		this.tokenid = tokenid;
		this.customerid = customerid;
		this.counterid = counterid;
		this.serviceid = serviceid;
		this.status = status;
	}

	public Long getTokenid() {
		return tokenid;
	}

	public void seTokenid(Long tokenid) {
		this.tokenid = tokenid;
	}

	public Long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}

	public Integer getCounterid() {
		return counterid;
	}

	public void setCounterid(Integer counterid) {
		this.counterid = counterid;
	}

	public Long getServiceid() {
		return serviceid;
	}

	public void setServiceid(Long serviceid) {
		this.serviceid = serviceid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public Customer getCustomerEntity() {
		return customerEntity;
	}

	public void setCustomerEntity(Customer customerEntity) {
		this.customerEntity = customerEntity;
	}
	public Counter getCounterEntity() {
		return counterEntity;
	}

	public void setCounterEntity(Counter counterEntity) {
		this.counterEntity = counterEntity;
	}

	public TurvoServices getServiceEntity() {
		return serviceEntity;
	}

	public void setServiceEntity(TurvoServices serviceEntity) {
		this.serviceEntity = serviceEntity;
	}
}
