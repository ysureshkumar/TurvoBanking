package com.pramati.turvo.banking.dao;

import java.util.List;

import com.pramati.turvo.banking.model.Users;

public interface UsersDAO {

	public Users getUser(String username,String password);
	
	public Users getUser(String username);
	
	public List<String> getRolenames(String username);
	
}
