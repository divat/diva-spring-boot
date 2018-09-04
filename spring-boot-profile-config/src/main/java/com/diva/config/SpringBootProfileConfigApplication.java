package com.diva.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class SpringBootProfileConfigApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringBootProfileConfigApplication.class, args);
		
		for(String name : ctx.getBeanDefinitionNames()){
			System.out.println("Active profile bean name :: " + name);
		}
	}
	
	@Profile("dev")
	@Bean
	public String devBean(){
		return "dev";
	}
	
	@Profile("qa")
	@Bean
	public String qaBean(){
		return "qa";
	}
	
	@Profile("prod")
	@Bean
	public String prodBean(){
		return "prod";
	}
}
