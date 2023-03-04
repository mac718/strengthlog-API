package com.mac718.strengthlogAPI.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDTO {
	
	@NotBlank(message = "Email should not be blank")
	@Email(message = "Email should be valid")
	private String email;
	
	@Size(min = 5, message = "Password must be at least 5 characters long")
	private String password;
	
	private String passwordConfirm;
}
