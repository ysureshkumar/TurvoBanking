package com.pramati.turvo.banking.serviceimpl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pramati.turvo.banking.model.Users;
import com.pramati.turvo.banking.service.UsersService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UsersService usersService;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users userInfo = usersService.getUser(username);
	
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+userInfo.getRolename().toUpperCase());

		User user = new User(userInfo.getUsername(), userInfo.getPassword(), Arrays.asList(authority));

		UserDetails userDetails = (UserDetails) user;
		
		return userDetails;
	}
	
	
	
}
