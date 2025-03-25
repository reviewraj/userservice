package com.userservice.response;

import com.userservice.enums.Gender;

import lombok.Data;
@Data
public class UserResponseDto {
	
	private Integer userId;

	private String userName;

	private String userEmail;

	private String phoneNumber;

	private Integer age;

	private Gender gender;
	
}
