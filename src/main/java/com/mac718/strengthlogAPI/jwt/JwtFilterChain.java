package com.mac718.strengthlogAPI.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Component
public class JwtFilterChain {
	
	private final JWTAuthFilter jwtAuthFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			http
				.authorizeHttpRequests(auth -> { auth
					.requestMatchers("/api/v1/auth/register", "/api/v1/auth/login").permitAll()
					.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
					.requestMatchers("/**").authenticated()
					;})//auth.anyRequest().authenticated(); })
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

}
