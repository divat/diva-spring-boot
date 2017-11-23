package com.diva.oauthexample.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.diva.oauthexample.dao.UserDao;
import com.diva.oauthexample.model.Role;
import com.diva.oauthexample.model.Users;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	UserDao userDao;
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		Users account = userDao.findByStringField("loginName", username).get(0);
		if(account == null){
			System.out.println("No such user " +username);
			throw new UsernameNotFoundException("No such user " +username);
		}else if(account.getRoles().isEmpty()){
			System.out.println("User " + username + " has such authorities");
			throw new UsernameNotFoundException("User " + username + " has such authorities");
		}
		
		boolean accountIsEnabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		return new User(account.getLoginName(), account.getPassword(), accountIsEnabled, accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(account.getRoles()));
	}
	
	public List<String> getRoleList(List<Role> roles){
		List<String> roleList = new ArrayList<String>();
		for(Role role : roles){
			roleList.add(role.getName());
		}
		return roleList;
	}
	
	public List<GrantedAuthority> getGrantedAuthorities(List<String> roles){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(String name : roles){
			authorities.add(new SimpleGrantedAuthority(name));
		}
		return authorities;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities(List<Role> list){
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoleList(list));
		return authList;
	}

}
