package com.pramati.turvo.banking.service;

import java.util.List;
import java.util.Optional;

import com.pramati.turvo.banking.model.Users;

public interface UsersService {
	
	public Users getUser(String username, String password);

	public Users getUser(String username);
	
	public Optional<Users> getOptionalUser(String username);

	public String getRolename(String username, String password);

	public String getRolename(String username);

	public List<String> getRolenames(String username);
}
