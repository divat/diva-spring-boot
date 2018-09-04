package com.diva.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.diva.config.app.AppConfiguration;

@SpringBootApplication
public class SpringBootConfigurationApplication {

	@Autowired
	AppConfiguration config;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootConfigurationApplication.class, args);
	}
	
	@PostConstruct
    public void init() {
        System.out.println(config.getAppName());
    }
}
