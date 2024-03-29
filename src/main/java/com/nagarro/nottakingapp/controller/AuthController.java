package com.nagarro.nottakingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.nottakingapp.auth.AuthenticationRequest;
import com.nagarro.nottakingapp.auth.AuthenticationService;
import com.nagarro.nottakingapp.auth.RegisterRequest;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	private AuthenticationService authService;
	
	@PostMapping("/signup")
	public ResponseEntity signup (@RequestBody RegisterRequest registerRequest)  //taking param of type RegisterRequest (DTO: Data Transfer Object)
														  // RegisterRequest will b a type of class to define 
														  //what king of data b transfered bw frontend and backend
	{
		authService.register(registerRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public com.nagarro.nottakingapp.auth.AuthenticationResponse login(@RequestBody AuthenticationRequest loginRequest)
	{
		return authService.authenticate(loginRequest);
	}
}
