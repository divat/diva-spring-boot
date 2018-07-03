package com.diva.ems.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.diva.ems.dao.UsersDao;
import com.diva.ems.model.UserProfile;
import com.diva.ems.model.Users;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	UsersDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userDao.findByStringValue("username", username);
		
		if(user == null){
			throw new UsernameNotFoundException("No such user " +username);
		}else if(user.getUserProfiles().isEmpty()){
			throw new UsernameNotFoundException("User " +username+ "has no authorities.");
		}
		
		return new User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user));
	}

	
	public List<GrantedAuthority> getGrantedAuthorities(Users user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(UserProfile userProfile: user.getUserProfiles()){
			authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
		}
		return authorities;
	}
}
