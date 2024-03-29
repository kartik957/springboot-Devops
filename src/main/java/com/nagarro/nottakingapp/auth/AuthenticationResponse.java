package com.nagarro.nottakingapp.auth;



import com.nagarro.nottakingapp.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

	private User user;      // if the person is authorized we ll have user
	
	private String token;   // the token will have the details(email,password) of user

}
