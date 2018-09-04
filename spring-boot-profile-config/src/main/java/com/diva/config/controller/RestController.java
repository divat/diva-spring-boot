package com.diva.config.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.diva.config.app.AppConfiguration;

/**
 * 
 * @author DHIVAKART
 *
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	AppConfiguration appConfig;
	
	@Value("${welcome.message}")
	private String welcomeMessage;

	@GetMapping("/welcome")
	public String retrieveWelcomeMessage() {
		// Complex Method
		return welcomeMessage;
	}
	
	@GetMapping(value = "/getConfig")
	public ResponseEntity<Map> dynamicConfiguration(){
		Map map = new HashMap();
		
		//List<String> uri = appConfig.getUri();
		
		map.put("appName", appConfig.getAppName());
		map.put("port", appConfig.getPort());
		map.put("uri", appConfig.getUri());
		map.put("status", appConfig.isStatus());
		
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
}
