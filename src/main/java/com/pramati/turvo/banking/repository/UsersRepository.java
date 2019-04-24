package com.pramati.turvo.banking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pramati.turvo.banking.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

	@Query("from Users u where u.username=? and u.password=?")
	public Users getUser(String username, String password);

	@Query("select u.rolename from Users u where u.username=?")
	public List<String> getRolenames(String username);

	@Query("from Users u where u.username=?")
	public Users getUser(String username);
}
