package com.userservice.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.applicationservice.response.ResponseDto;

@FeignClient(value = "DOCTORSERVICE", path = "/bookmydoctor/api/doctor")
public interface DocterService {
	@GetMapping("/getAll")
	public ResponseEntity<ResponseDto> getAllUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "doctorName") String sortBy,
			@RequestParam(defaultValue = "asc") String sortDir);

	@GetMapping("/search")
	public ResponseEntity<ResponseDto> searchDoctors(@RequestParam(required = false) String doctorName,
			@RequestParam(required = false) String speciaList, @RequestParam(required = false) Double minRating);
	@PutMapping("/rating")
    public ResponseEntity<ResponseDto> rateDoctors(@RequestParam  Double rating,@RequestParam String doctorEmail) ;
}
