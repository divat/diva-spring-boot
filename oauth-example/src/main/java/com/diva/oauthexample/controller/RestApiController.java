package com.diva.oauthexample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

	/**
	 * Get list of cms api's
	 * @return
	 */
	@RequestMapping(value = "/", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> home(Model model){
		return new ResponseEntity<>("Rest", HttpStatus.OK);
	}
}
