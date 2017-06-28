package com.packt.webstore.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3935230281455340039L;
	private String username;
	
	public UserNotFoundException(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	
}
