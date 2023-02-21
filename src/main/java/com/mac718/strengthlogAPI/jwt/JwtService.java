package com.mac718.strengthlogAPI.jwt;

import org.springframework.security.core.Authentication;

import com.mac718.strengthlogAPI.user.UserEntity;

public interface JwtService {
	public abstract String register(UserEntity user) throws Exception;
	public abstract String login (UserEntity user);
	public abstract String createToken(Authentication authentication);
	public abstract Object createScope(Authentication authentication);
}
