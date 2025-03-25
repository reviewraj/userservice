package com.userservice.exception;

public class NoDoctorsAvailable extends RuntimeException {

	public NoDoctorsAvailable(String message) {
		super(message);
	}

}
