package com.diva.ems.dao;

import org.springframework.stereotype.Repository;

import com.diva.ems.model.Users;

@Repository
public class UsersDao extends Dao<Users>{

	public UsersDao(Users user) {
		super(Users.class);
	}

}
