package com.userservice.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.applicationservice.response.ResponseDto;

@RestControllerAdvice
public class GlobalExceptionalHandler {
	@ExceptionHandler(UserAlreadyExist.class)
	public ResponseEntity<ResponseDto> userAlreadyExists(UserAlreadyExist userAlreadyExist) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(  new ResponseDto(false, userAlreadyExist.getMessage(), null));
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDto> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
	    Map<String,String> map = new HashMap<>();
	    for(FieldError fieldError:methodArgumentNotValidException.getBindingResult().getFieldErrors())
	    {
	    	map.put(fieldError.getField(),fieldError.getDefaultMessage());
	    }
	   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(  new ResponseDto(false, "please pass the valid arguments", map));
	}
	@ExceptionHandler(UserNotExist.class)
	public ResponseEntity<ResponseDto> userNotExists(UserNotExist userNotExist) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(  new ResponseDto(false, userNotExist.getMessage(), null));
	}
	@ExceptionHandler(PasswordMismatch.class)
	public ResponseEntity<ResponseDto> passwordMismatch(PasswordMismatch passwordMismatch) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(  new ResponseDto(false, passwordMismatch.getMessage(), null));
	}
	@ExceptionHandler(DoctorAlreadyExists.class)
	public ResponseEntity<ResponseDto> doctorAlreadyExists(DoctorAlreadyExists passwordMismatch) {
		return ResponseEntity.status(HttpStatus.FOUND).body(  new ResponseDto(false, passwordMismatch.getMessage(), null));
	}
	@ExceptionHandler(NoDoctorsAvailable.class)
	public ResponseEntity<ResponseDto> passwordMismatch(NoDoctorsAvailable passwordMismatch) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(   new ResponseDto(false, passwordMismatch.getMessage(), null));
	}
	@ExceptionHandler(NoAppointmentsFound.class)
	public ResponseEntity<ResponseDto> noAppointmentsFound(NoAppointmentsFound passwordMismatch) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new ResponseDto(false, passwordMismatch.getMessage(), null));
	}
	@ExceptionHandler(InternalServerError.class)
	public ResponseEntity<ResponseDto> noAppointmentsFound(InternalServerError passwordMismatch) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( new ResponseDto(false, passwordMismatch.getMessage(), null));
	}
	
}
