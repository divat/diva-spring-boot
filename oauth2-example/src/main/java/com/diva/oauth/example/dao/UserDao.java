package com.diva.oauth.example.dao;

import org.springframework.stereotype.Repository;
import com.diva.oauth.example.config.database.Dao;
import com.diva.oauth.example.model.User;

@Repository
public class UserDao extends Dao<User>{

	public UserDao(){
		super(User.class);
	}
}
