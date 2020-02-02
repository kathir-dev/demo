package com.coding.app.demo.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.coding.app.demo.security.jwt.JwtConfigurer;
import com.coding.app.demo.security.jwt.JwtTokenProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	JwtTokenProvider provider;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity sec) throws Exception {
		sec.httpBasic().disable()
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
		.antMatchers("/auth/authenticate").permitAll()
		.antMatchers(HttpMethod.GET, "/people/**").hasRole("USER")
		.antMatchers(HttpMethod.POST, "/people/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/people/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/people/**").hasRole("ADMIN")
		.anyRequest().authenticated().and()
		.apply(new JwtConfigurer(provider));
	}

}
