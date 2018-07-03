package com.diva.ems.exception;

public class CustomErrorType {

	private String message;
	
	public CustomErrorType(String message){
		this.message = message;
	}
	
	public String getErrorMessage(){
		return message;
	}
}
