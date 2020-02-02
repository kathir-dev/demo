package com.coding.app.demo.security.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.coding.app.demo.repository.UserRepository;

@Component
public class ApplicationUserDetailsService implements UserDetailsService {
	
	private UserRepository userRepo;
	
	public ApplicationUserDetailsService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.userRepo.findByUsername(username)
	            .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
	}
}
