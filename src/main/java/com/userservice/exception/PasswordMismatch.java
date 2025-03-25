package com.userservice.exception;

public class PasswordMismatch extends RuntimeException {

	public PasswordMismatch(String message) {
		super(message);
	}

}
