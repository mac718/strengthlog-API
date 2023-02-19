package com.mac718.strengthlogAPI.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mac718.strengthlogAPI.jwt.JwtService;
import com.mac718.strengthlogAPI.user.UserEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@EnableMethodSecurity
@RequestMapping("/api/v1/auth")
public class AuthController {
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/register")
	public ResponseEntity<JwtResponse> registerUser(@RequestBody UserEntity user) throws Exception {
		return new ResponseEntity<JwtResponse>(new JwtResponse(jwtService.register(user)), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> loginUser(@RequestBody UserEntity user) throws Exception {
		return new ResponseEntity<JwtResponse>(new JwtResponse(jwtService.login(user)), HttpStatus.OK);
	}
	
	record JwtResponse(String token) {}

}
