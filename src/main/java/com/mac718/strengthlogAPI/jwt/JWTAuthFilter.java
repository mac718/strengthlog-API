package com.mac718.strengthlogAPI.jwt;

import java.io.IOException;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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
	

	//private final JwtService jwtService;
	private final CustomUserDetailsService userDetailsService;
	private final JwtDecoder jwtDecoder;
	

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
		Map<String, Object> decoded;
		
		try {
			decoded = jwtDecoder.decode(jwt).getClaims();
		} catch(Exception e) {
			throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
		}
		
		userEmail = (String) decoded.get("sub");
		
		if (userEmail != null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		
		filterChain.doFilter(request, response);
		
	}

}
