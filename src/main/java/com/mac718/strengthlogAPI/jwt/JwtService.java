package com.mac718.strengthlogAPI.jwt;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.mac718.strengthlogAPI.jpa.UserRepository;
import com.mac718.strengthlogAPI.user.Role;
import com.mac718.strengthlogAPI.user.User;

@Service
public class JwtService {
	
	private UserRepository userRepository;

	private JwtEncoder jwtEncoder;

	private BCryptPasswordEncoder passwordEncoder;
	
	

	
	public JwtService(UserRepository userRepository, JwtEncoder jwtEncoder, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.jwtEncoder = jwtEncoder;
		this.passwordEncoder = passwordEncoder;
	}

	public String register(@RequestBody User user, Authentication authentication) throws Exception {
		System.out.println(user);
		Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
		
		if (existingUser.isPresent()) {
			throw new Exception("User with email " + user.getEmail() + " already exists.");
		}
		
		System.out.println(authentication.getPrincipal());
		
		User newUser = User.builder()
						.email(user.getEmail())
						.password(user.getPassword())
						.role(Role.USER)
						.build();
		
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		
		
		
		userRepository.save(newUser);
		
		
		return createToken(authentication);
	}
	
	public String createToken(Authentication authentication) {
		var claims = JwtClaimsSet.builder()
								.issuer("self")
								.issuedAt(Instant.now())
								.expiresAt(Instant.now().plusSeconds(60 * 30))
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
