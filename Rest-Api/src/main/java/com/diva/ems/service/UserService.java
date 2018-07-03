package com.diva.ems.service;

import java.util.List;
import com.diva.ems.model.User;

public interface UserService {

	List<User> listUsers();
	
	User findById(Long id);
	
	User findByName(String name);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserById(Long id);
	
	void deleteAllUsers();
	
	boolean isUserExist(User id);
}
