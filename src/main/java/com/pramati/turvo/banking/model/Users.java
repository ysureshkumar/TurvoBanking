package com.pramati.turvo.banking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class Users {

	@Id
	@Column(name = "userid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userid;

	@Column(name = "active")
	@NotBlank
	private Long active;

	@Column(name = "username")
	@NotBlank
	private String username;

	@Column(name = "password")
	@NotBlank
	private String password;

	@Column(name = "rolename")
	@NotBlank
	private String rolename;

	public Users() {
		super();
	}

	public Users(Users applicationUser) {
		super();
		this.userid = applicationUser.userid;
		this.active = applicationUser.active;
		this.username = applicationUser.username;
		this.password = applicationUser.password;
		this.rolename = applicationUser.rolename;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getActive() {
		return active;
	}

	public void setActive(Long active) {
		this.active = active;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
}
