package com.userservice.service;

import com.userservice.request.UserRequestDto;
import com.userservice.response.UserResponseDto;

public interface UserService {

	UserResponseDto save(UserRequestDto userRequestDto);

	UserResponseDto update(UserRequestDto userRequestDto);

	UserResponseDto delete(UserRequestDto userRequestDto);

	

}
