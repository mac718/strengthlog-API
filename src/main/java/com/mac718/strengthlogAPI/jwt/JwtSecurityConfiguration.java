package com.mac718.strengthlogAPI.jwt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mac718.strengthlogAPI.jpa.UserRepository;
import com.mac718.strengthlogAPI.user.UserNotFoundException;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class JwtSecurityConfiguration {
	
	private final UserRepository userRepository;
	private final JWTAuthFilter jwtAuthFilter;
	private final CustomUserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			http
				.authorizeHttpRequests(auth -> { auth
					.requestMatchers("/api/v1/auth/register").anonymous()
					.requestMatchers("/h2-console/**").permitAll().anyRequest().anonymous();})//auth.anyRequest().authenticated(); })
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.httpBasic()
				.and()
				.csrf().disable()
				.headers().frameOptions().sameOrigin()
				.and()
				.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
			
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
				
				return http.build();
	}
	
//	
//	@Bean
//	public UserDetailsService userDetailsService() {
//	    UserDetails user = User.withUsername("user")
//	            .password("password")
//	            .passwordEncoder(str -> passwordEncoder().encode(str))
//	            .roles("USER")
//	            .build();
//	    
//	    UserDetails admin = User.withUsername("admin")
//	            .password("password")
//	            .passwordEncoder(str -> passwordEncoder().encode(str))
//	            .roles("ADMIN")
//	            .build();
//	    
//	    return new InMemoryUserDetailsManager(user, admin);
//	    
//	}
	
	
	
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		System.out.println("hello from userdetails");
//		return username -> userRepository.findByEmail(username)
//				.orElseThrow(() -> new UserNotFoundException("User not found"));
//	}
	
//	@Bean
//	  public UserDetailsService userDetailsService() {
//	    return username -> userRepository.findByEmail(username)
//	        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//	  }

	  @Bean
	  public AuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(passwordEncoder());
	    return authProvider;
	  }
	
	 @Bean
	 public AuthenticationManager authenticationManager(
	         AuthenticationConfiguration authenticationConfiguration) throws Exception {
	     return authenticationConfiguration.getAuthenticationManager();
	 }
//	
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public KeyPair keyPair() {
		try {
			var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			return keyPairGenerator.generateKeyPair();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Bean
	public RSAKey rsaKey(KeyPair keyPair) {
		return new RSAKey
				.Builder((RSAPublicKey) keyPair.getPublic())
				.privateKey(keyPair.getPrivate())
				.keyID(UUID.randomUUID().toString())
				.build();
	}
	
	@Bean 
	public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
		JWKSet jwkSet = new JWKSet(rsaKey);
		
		return (jwtSelector, context) -> jwtSelector.select(jwkSet);
	}
	
	
	@Bean
	public JwtDecoder jwkDecoder(RSAKey rsaKey) throws JOSEException {
		return NimbusJwtDecoder
				.withPublicKey(rsaKey.toRSAPublicKey())
				.build();
	}
	
	
	@Bean
	public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
		return new NimbusJwtEncoder(jwkSource);
	}

}
