package com.diva.ems.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.diva.ems.model.User;
import com.diva.ems.model.Users;

@Service("userService")
public class UserServiceImpl implements UserService{

	private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	static{
		users = populateUsers();
	}
	
	@Override
	public List<User> listUsers() {
		return users;
	}

	@Override
	public User findById(Long id) {
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}

	@Override
	public User findByName(String name) {
		for(User user : users){
			if(user.getName().equalsIgnoreCase(name) ){
				return user;
			}
		}
		return null;
	}

	@Override
	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
	}

	@Override
	public void updateUser(User user) {
		int update = users.indexOf(user);
		users.set(update, user);
	}

	@Override
	public void deleteUserById(Long id) {
		for(User user : users){
			if(user.getId() == id){
				users.remove(id);
			}
		}
	}

	@Override
	public void deleteAllUsers() {
		users.clear();
	}

	@Override
	public boolean isUserExist(User user) {
		return findByName(user.getName()) != null;
	}
	
	private static List<User> populateUsers(){
		List<User> users = new ArrayList<User>(); 
		users.add(new User(counter.incrementAndGet(), "diva", 32, 85000));
		users.add(new User(counter.incrementAndGet(), "kavi", 27, 45000));
		users.add(new User(counter.incrementAndGet(), "thiyagarajan", 57, 185000));
		users.add(new User(counter.incrementAndGet(), "jahan", 32, 100000));
		return users;
	}

}
