package com.pramati.turvo.banking.model;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "service")
@EntityListeners(AuditingEntityListener.class)
public class TurvoServices {

	@Id
	@Column(name = "serviceid")
	private Long serviceId;

	@Column(name = "servicename")
	private String serviceName;

	public TurvoServices() {
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public TurvoServices(Long serviceId, String serviceName) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
	}
}
