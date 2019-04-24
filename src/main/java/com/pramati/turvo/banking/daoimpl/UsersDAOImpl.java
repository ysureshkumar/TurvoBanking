package com.pramati.turvo.banking.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pramati.turvo.banking.dao.UsersDAO;
import com.pramati.turvo.banking.model.Users;
import com.pramati.turvo.banking.repository.UsersRepository;

@Service
public class UsersDAOImpl implements UsersDAO {

	@Autowired
	UsersRepository usersRepository;

	public void setUserRepository(UsersRepository applicationUserRepository) {
		this.usersRepository = applicationUserRepository;
	}

	public Users getUser(String username, String password) {
		return usersRepository.getUser(username, password);
	}

	public Users getUser(String username) {
		return usersRepository.getUser(username);
	}

	public List<String> getRolenames(String username) {
		return usersRepository.getRolenames(username);
	}

}
