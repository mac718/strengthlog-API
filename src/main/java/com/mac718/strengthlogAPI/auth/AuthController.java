package com.mac718.strengthlogAPI.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mac718.strengthlogAPI.jwt.JwtServiceImpl;
import com.mac718.strengthlogAPI.user.UserEntity;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final JwtServiceImpl jwtService;
	
	@PostMapping("/register")
	public ResponseEntity<JwtResponse> registerUser(@Valid @RequestBody UserEntity user) throws Exception {
		return new ResponseEntity<JwtResponse>(new JwtResponse(jwtService.register(user)), HttpStatus.CREATED);	
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> loginUser(@Valid @RequestBody UserEntity user, HttpServletResponse res) throws Exception {
		String name;
		
		if (user.getName() != null) {
			name = user.getName();
		} else {
			name = user.getEmail();
		}
		
		
		String token = jwtService.login(user);
		
		Cookie userName = new Cookie("name", name);
		userName.setSecure(true);
		userName.setHttpOnly(true);
		userName.setPath("/");
		res.addCookie(userName);
		
		Cookie userToken = new Cookie("token", token);
		userToken.setSecure(true);
		userToken.setHttpOnly(true);
		userToken.setPath("/");
		res.addCookie(userToken);
		
		ResponseEntity<JwtResponse> response = new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.OK);
			
		return response;
	}
	
	record JwtResponse(String token) {}

}
