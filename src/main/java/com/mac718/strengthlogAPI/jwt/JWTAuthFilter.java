package com.mac718.strengthlogAPI.jwt;

import java.io.IOException;

import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JWTAuthFilter extends OncePerRequestFilter{
	private JwtService jwtService;
	private CustomUserDetailsService userDetailsService;
	private JwtDecoder jwtDecoder;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		String jwt;
		String userEmail;
		
		if (authHeader == null || !authHeader.startsWith("Bearer")) {
			System.out.println("hello from filter");
			filterChain.doFilter(request, response);
			return;
		}
		
		jwt = authHeader.substring(7);
		jwtDecoder.decode(jwt);
		userEmail = jwtService.extractUsername(jwt);
		
		
	}

}
