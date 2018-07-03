package com.diva.ems.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.diva.ems.exception.CustomErrorType;
import com.diva.ems.model.User;
import com.diva.ems.model.Users;
import com.diva.ems.service.UserService;

@RestController
@RequestMapping("/api/")
public class MainController {
	public static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	UserService userService;
	
	/**
	 * Retrieve all users
	 * @return
	 */
	@GetMapping(value = "/user/")
	public ResponseEntity<List<User>> listAllUsers(){
		List<User> users = userService.listUsers();
		if(users.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	/**
	 * Retrieve user by id
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") long id){
		logger.info("Fetching user with id {}", id);
		User user = userService.findById(id);
		if(user == null){
			return new ResponseEntity(new CustomErrorType("User with the id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	/**
	 * Create new user
	 * @param user
	 * @param ucBuilder
	 * @return
	 */
	@PostMapping(value = "/user/")
	public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder){
		logger.info("Creating user {} " +user);
		if(userService.isUserExist(user)){
			logger.error("Unable to create. A User with name {} already exist", user.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A user with name "+user.getName()+ "already exist."), HttpStatus.CONFLICT);
		}
		
		userService.saveUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	/**
	 * Update user
	 * @param id
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		logger.info("Updating User with id {}", id);

		User currentUser = userService.findById(id);

		if (currentUser == null) {
			logger.error("Unable to update. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentUser.setName(user.getName());
		currentUser.setAge(user.getAge());
		currentUser.setSalary(user.getSalary());

		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}
}
