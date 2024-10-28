package com.antoniorg.spaceship.application.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.antoniorg.spaceship.infrastructure.repository.UserRepository;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {
	private @Autowired UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		return new User(user.getUsername(), user.getPwd(), Collections.emptySet());
	}
}
