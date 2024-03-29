package com.nagarro.nottakingapp.auth;

import lombok.Data;

@Data
public class RegisterRequest 
{
	private String fullname;
	private String password;
	private String email;
}
