package com.nagarro.nottakingapp.auth;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nagarro.nottakingapp.entities.Role;
import com.nagarro.nottakingapp.entities.User;
import com.nagarro.nottakingapp.exceptions.UserAlreadyExist;
import com.nagarro.nottakingapp.repository.UserRepository;
import com.nagarro.nottakingapp.service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserRepository repository;

	private final PasswordEncoder passwordEncoder;

	private final JwtService jwtService;

	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse register(RegisterRequest request) throws UserAlreadyExist {

//		var checkUser = repository.findByEmail(request.getEmail());
//		if(checkUser!=null) {
//			throw new UserAlreadyExist("User Already Exists", checkUser.get().getEmail());
//		}
//		System.out.println(checkUser);
		var user = User.builder().fullName(request.getFullname()).email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword())).role(Role.USER).build();
		System.out.println(user);
		repository.save(user);
		var jwtToken = jwtService.generateToken(user);

		return AuthenticationResponse.builder().token(jwtToken).build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user = repository.findByEmail(request.getEmail()).orElseThrow();

		// genrating token
		var jwtToken = jwtService.generateToken(user);

		return AuthenticationResponse.builder().token(jwtToken).user(user).build();
	}
	
}
