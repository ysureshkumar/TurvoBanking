package com.pramati.turvo.banking.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.pramati.turvo.banking.dao.UsersDAO;
import com.pramati.turvo.banking.model.Users;
import com.pramati.turvo.banking.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersDAO usersDAO;

	public void setUsersDAO(UsersDAO UsersDAO) {
		this.usersDAO = UsersDAO;
	}

	public Users getUser(String username, String password) {

		return usersDAO.getUser(username, password);
	}

	public Users getUser(String username) {

		return usersDAO.getUser(username);
	}

	public String getRolename(String username, String password) {

		Users user = usersDAO.getUser(username, password);
		return user.getRolename();
	}

	public String getRolename(String username) {

		Users user = usersDAO.getUser(username);
		return user.getRolename();
	}

	public List<String> getRolenames(String username) {

		return usersDAO.getRolenames(username);
	}

	public Optional<Users> getOptionalUser(String username) {

		Users Users = usersDAO.getUser(username);
		
		return Optional.of(Users);
	}
	
	
}
