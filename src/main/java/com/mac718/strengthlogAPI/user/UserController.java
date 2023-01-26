package com.mac718.strengthlogAPI.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mac718.strengthlogAPI.jpa.UserRepository;

@RestController
public class UserController {
	private UserRepository repository;

	public UserController(UserRepository repository) {
		super();
		this.repository = repository;
	}
	
	@GetMapping("/users")
	public List<User> getAll() {
		return repository.findAll();
	}
	
	@PostMapping("/api/1.0/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = repository.save(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}

}
