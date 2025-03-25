package com.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.repository.DocterService;
import com.userservice.request.UserRequestDto;
import com.userservice.response.ResponseDto;
import com.userservice.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService  userService;
	@Autowired
	private DocterService docterService;
	@PostMapping("/save")
	public ResponseEntity<ResponseDto> saveUser(@RequestBody UserRequestDto userRequestDto ) {
		return ResponseEntity.ok (new ResponseDto(false,"user created successfully",userService.save(userRequestDto)));
	}
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateUser(@RequestBody UserRequestDto userRequestDto ) {
		return ResponseEntity.ok ( new ResponseDto(false,"user updated successfully",userService.update(userRequestDto)));
	}
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteUser(@RequestBody UserRequestDto userRequestDto ) {
		return ResponseEntity.ok ( new ResponseDto(false,"user deleted successfully",userService.delete(userRequestDto)));
	}
	@GetMapping("/getAll")
	public ResponseEntity<ResponseDto> getAllUsers(
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "doctorName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir)  {
		return docterService.getAllUsers(page,size,sortBy,sortDir);
	}
	@GetMapping("/search")
    public ResponseEntity<ResponseDto> searchDoctors(
            @RequestParam(required = false) String doctorName,
            @RequestParam(required = false) String speciaList,
            @RequestParam(required = false) Double minRating) {
            
		return docterService.searchDoctors(doctorName, speciaList, minRating);
            
        }
	@PatchMapping("/rating")
    public ResponseEntity<ResponseDto> searchDoctors(@RequestParam  Double rating,@RequestParam String doctorEmail) {
            
           return  docterService.rateDoctors(rating, doctorEmail);
            
        }
}
