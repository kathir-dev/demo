package com.coding.app.demo.controller;


import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding.app.demo.entity.AuthenticationRequest;
import com.coding.app.demo.repository.UserRepository;
import com.coding.app.demo.security.jwt.JwtTokenProvider;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	JwtTokenProvider provider;

	@Autowired
	UserRepository userRepo;

	@PostMapping("/authenticate")
	public ResponseEntity login(@RequestBody AuthenticationRequest data) {
		try {
			String username = data.getUsername();
			authManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));

			String token = provider.createToken(username, this.userRepo.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());
			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			return ok(model);
		} catch (AuthenticationException ae) {
			throw new BadCredentialsException(" Invalid username/password ");
		}
	}

}
