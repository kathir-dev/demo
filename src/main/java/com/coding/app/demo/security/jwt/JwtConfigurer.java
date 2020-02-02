package com.coding.app.demo.security.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfigurer extends 
SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>
{
	private JwtTokenProvider provider;
	
	public JwtConfigurer(JwtTokenProvider provider) {
		this.provider = provider;
	}
	
	public void configure(HttpSecurity sec) throws Exception {
		JwtTokenFilter filter = new JwtTokenFilter(provider);
		sec.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}
}
