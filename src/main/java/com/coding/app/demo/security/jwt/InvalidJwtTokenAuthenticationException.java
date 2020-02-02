package com.coding.app.demo.security.jwt;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtTokenAuthenticationException extends AuthenticationException {
	
	public InvalidJwtTokenAuthenticationException(String e) {
		super(e);
	}

}
