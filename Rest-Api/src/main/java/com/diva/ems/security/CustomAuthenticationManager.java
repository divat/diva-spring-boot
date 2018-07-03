package com.diva.ems.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationManager implements AuthenticationManager{
	
	@Autowired
	private CustomAuthenticationProvider authenticationProvider;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		return authenticationProvider.authenticate(auth);
	}

}
