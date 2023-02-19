package com.mac718.strengthlogAPI.jwt;

import com.mac718.strengthlogAPI.user.UserEntity;

//import com.mac718.strengthlogAPI.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mac718.strengthlogAPI.jpa.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	private final UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("hellow from thing");
		UserEntity user = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		
		return new User(user.getUsername(), user.getPassword(),user.getAuthorities());
	}


}
