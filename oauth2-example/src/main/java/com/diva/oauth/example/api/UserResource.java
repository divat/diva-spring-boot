package com.diva.oauth.example.api;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author DHIVAKART
 *
 */
@RestController
public class UserResource {

	/**
	 * Get the user details
	 * @param principal
	 * @return
	 */
	@GetMapping(value = "/user")
	public Principal getPrincipal(Principal principal){
		return principal;
	}
}
