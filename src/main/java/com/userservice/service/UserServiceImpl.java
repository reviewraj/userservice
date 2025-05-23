package com.userservice.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.applicationservice.response.ResponseDto;
import com.applicationservice.response.UserResponseDto;
import com.userservice.entity.User;
import com.userservice.enums.Status;
import com.userservice.exception.PasswordMismatch;
import com.userservice.exception.UserAlreadyExist;
import com.userservice.exception.UserNotExist;
import com.userservice.repository.UserRepository;
import com.userservice.request.UserRequestDto;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private KafkaTemplate<String,ResponseDto> kafkaTemplate;

	@Override
	public UserResponseDto save(UserRequestDto userRequestDto) {
		Optional<User> optional = userRepository.findByUserEmail(userRequestDto.getUserEmail());
		if (optional.isPresent())
			throw new UserAlreadyExist("user already exits with this email : " + userRequestDto.getUserEmail());
		User userEntity = new User();
		modelMapper.map(userRequestDto, userEntity);
		User dbUser = userRepository.save(userEntity);
		UserResponseDto userResponseDto = new UserResponseDto();
		modelMapper.map(dbUser, userResponseDto);
		kafkaTemplate.send("user-creation",new ResponseDto(false, null, userResponseDto));
		return userResponseDto;

	}

	@Override
	public UserResponseDto update(UserRequestDto userRequestDto) {
		Optional<User> optional = userRepository.findById(userRequestDto.getUserId());
		if (optional.isEmpty())
			throw new UserNotExist("user not exits with this email : " + userRequestDto.getUserEmail()
					+ " please create the user first");

		else if (!optional.get().getPassword().equals(userRequestDto.getPassword())) {
			throw new PasswordMismatch("please enter associated password with : " + userRequestDto.getUserEmail());
		}
		User userEntity = optional.get();
		modelMapper.map(userRequestDto, userEntity);
		User dbUser = userRepository.save(userEntity);
		UserResponseDto userResponseDto = new UserResponseDto();
		modelMapper.map(dbUser, userResponseDto);
		kafkaTemplate.send("user-updation",new ResponseDto(false, null, userResponseDto));
		return userResponseDto;

	}

	@Override
	public UserResponseDto delete(UserRequestDto userRequestDto) {
		Optional<User> optional = userRepository.findByUserEmail(userRequestDto.getUserEmail());
		if (optional.isEmpty())
			throw new UserNotExist("user not exits with this email : " + userRequestDto.getUserEmail());

		else if (!optional.get().getPassword().equals(userRequestDto.getPassword())) {
			throw new PasswordMismatch("please enter associated password with : " + userRequestDto.getUserEmail());
		}
		User userEntity = optional.get();
		if(userEntity.getStatus()==Status.INACTIVE)throw new UserNotExist("user not exits with this email : " + userRequestDto.getUserEmail());
		userEntity.setStatus(Status.INACTIVE);
		User dbUser = userRepository.save(userEntity);
		UserResponseDto userResponseDto = new UserResponseDto();
		modelMapper.map(dbUser, userResponseDto);
		return userResponseDto;

	}
//	@CircuitBreaker(name = "myCircuitBreakerforUser", fallbackMethod = "fallbackResponse")
//	@Retry(name = "retryforUser",fallbackMethod = "fallbackResponse")
//    @TimeLimiter(name = "timelimitforUser")
	public Optional<User> getUserById(Integer userId) {
		return userRepository.findById(userId);
			
	}
	
//	public CompletableFuture<Optional<User>> fallbackResponse(Integer userId, Throwable e) {
//        User user = new User();
//        user.setUserId(userId);
//        user.setUserName("Unavailable user");
//       return  CompletableFuture.supplyAsync(() -> Optional.of(user));
//    }

}
