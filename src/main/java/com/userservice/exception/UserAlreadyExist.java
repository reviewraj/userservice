package com.userservice.exception;

import lombok.Data;

@Data
public class UserAlreadyExist extends RuntimeException {
private String message;

public UserAlreadyExist(String message) {
	super(message);
	this.message = message;
}

}
