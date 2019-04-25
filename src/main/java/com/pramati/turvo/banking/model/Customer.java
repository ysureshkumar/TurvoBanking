package com.pramati.turvo.banking.model;

import java.io.IOException;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
public class Customer {

	@Id
	@Column(name = "customerid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerid;

	@NotBlank
	private String name;

	@NotBlank
	private String phno;

	@NotBlank
	private String servicetype;

	@NotBlank
	private String address;
	

	public Long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getServicetype() {
		return servicetype;
	}

	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
