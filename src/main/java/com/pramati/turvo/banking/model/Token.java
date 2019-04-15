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

	public String toString() {
		return tokenid + "-" + customerid + "-" + counterid + "-" + serviceid + "-" + status;
	}
}
