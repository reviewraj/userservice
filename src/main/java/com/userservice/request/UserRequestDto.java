package com.userservice.request;

import com.userservice.enums.Gender;
import com.userservice.enums.Role;
import com.userservice.enums.Status;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDto {
	@Size(min = 3, max = 50, message = "length of characters must be between greater tha 3 and lessan than 50 ")
	@NotBlank(message = "username cannot be null or space")
	private String userName;

	@NotBlank(message = "email cannot be null or space")
	@Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}$", message = "please enter the valid email")
	private String userEmail;
    @Min(value = 10)
	@NotBlank(message = "phonenumber cannot be null or space")
	@Pattern(regexp = "^(?:\\+91|91|0)?[6-9]\\d{9}$", message = "please provide valid mobile number")
	private String phoneNumber;

	private Integer age;

	private Gender gender;

	@NotBlank(message = "password cannot be null or space")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&*!])[A-Za-z\\d@#$%^&*!]{8,}$", message = "please use strong password")
	private String password;

	private Status status = Status.ACTIVE;

}
