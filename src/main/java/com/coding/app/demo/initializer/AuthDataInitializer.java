package com.coding.app.demo.initializer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.coding.app.demo.entity.User;
import com.coding.app.demo.repository.UserRepository;

@Component
public class AuthDataInitializer implements CommandLineRunner {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PasswordEncoder encoder;

	@Override
	public void run(String... args) throws Exception {
		
		Set<String> userRoles = new HashSet<>();
		userRoles.add("ROLE_USER");
		
		
		this.userRepo.save(new User("user", this.encoder.encode("password"), userRoles));
		
		Set<String> adminRoles = new HashSet<>();
		adminRoles.add("ROLE_USER");
		adminRoles.add("ROLE_ADMIN");
		
		this.userRepo.save(new User("admin", this.encoder.encode("password"), adminRoles));
		
	}

}
