package com.userservice.service;

import java.util.Optional;

import com.applicationservice.response.UserResponseDto;
import com.userservice.entity.User;
import com.userservice.request.UserRequestDto;

public interface UserService {

	UserResponseDto save(UserRequestDto userRequestDto);

	UserResponseDto update(UserRequestDto userRequestDto);

	UserResponseDto delete(UserRequestDto userRequestDto);

	Optional<User> getUserById(Integer userEmail);

	

}
