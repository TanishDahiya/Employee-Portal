package com.app.ticketmanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.ticketmanagement.config.CustomUserDetails;
import com.app.ticketmanagement.dao.UserRepository;
import com.app.ticketmanagement.models.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		

		//fetching user from database
	    User user= userRepository.getUserByUserName(username);

		if (user == null) {
			throw new UsernameNotFoundException("Could not found user !!");
		}

		CustomUserDetails customUserDetails = new CustomUserDetails(user);

		return customUserDetails;
	}

}
