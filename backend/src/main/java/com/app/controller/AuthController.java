package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.RegDTO;
import com.app.service.AuthService;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("/reg")
@AllArgsConstructor
public class AuthController {

	@Autowired
	AuthService service;
	
	@PostMapping("/new")
	public ResponseEntity<ApiResponse> register(@RequestBody RegDTO dto)
	{
		ApiResponse apiResponse = service.register(dto);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
}
