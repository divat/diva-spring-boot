package com.diva.oauth.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.diva.oauth.example.dao.UserDao;
import com.diva.oauth.example.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	private UserDao userDao;
	
	@Autowired
	public CustomUserDetailsService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByStringValue("username", username);
		
		if(user == null){
			throw new UsernameNotFoundException("No such user :: " +username);
		}
		
		boolean accountIsEnabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), accountIsEnabled, accountNonExpired, credentialsNonExpired, accountNonLocked, AuthorityUtils.createAuthorityList("ROLE_USER"));
	}

}
