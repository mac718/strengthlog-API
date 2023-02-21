package com.mac718.strengthlogAPI.jwt;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.mac718.strengthlogAPI.jpa.UserRepository;
import com.mac718.strengthlogAPI.user.Role;
import com.mac718.strengthlogAPI.user.UserEntity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class JwtServiceImpl implements JwtService {
	
	private UserRepository userRepository;

	private JwtEncoder jwtEncoder;

	private BCryptPasswordEncoder passwordEncoder;
	
	private AuthenticationManager authenticationManager;
	
	@Override
	public String register(@RequestBody UserEntity user) throws Exception {
		System.out.println(user);
		Optional<UserEntity> existingUser = userRepository.findByEmail(user.getEmail());
		
		if (existingUser.isPresent()) {
			throw new Exception("User with email " + user.getEmail() + " already exists.");
		}
		
		UserEntity newUser = UserEntity.builder()
						.email(user.getEmail())
						.password(user.getPassword())
						.role(Role.USER)
						.build();
		
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		
		userRepository.save(newUser);
		
		//return createToken(authentication);
		return "success";
	}
	
	@Override
	public String login(@RequestBody UserEntity user) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						user.getUsername(), user.getPassword()
						)
				);
		
		userRepository.findByEmail(user.getEmail()).orElseThrow();
		
		System.out.println("authhhhhhh" + authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return createToken(authentication);
		
	}
	
	public String createToken(Authentication authentication) {
		var claims = JwtClaimsSet.builder()
								.issuer("self")
								.issuedAt(Instant.now())
								.expiresAt(Instant.now().plusSeconds(60 * 10))
								.subject(authentication.getName())
								.claim("scope", createScope(authentication))
								.build();
		return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
								
	}
	
	public Object createScope(Authentication authentication) {
		return authentication.getAuthorities().stream()
							.map(a -> a.getAuthority())
							.collect(Collectors.joining(" "));
	}	
}
