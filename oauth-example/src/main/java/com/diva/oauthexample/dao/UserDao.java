package com.diva.oauthexample.dao;

import org.springframework.stereotype.Repository;
import com.diva.oauthexample.config.Dao;
import com.diva.oauthexample.model.Users;

@Repository
public class UserDao extends Dao<Users>{

	public UserDao(){
		super(Users.class);
	}
}
