package com.mac718.strengthlogAPI.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mac718.strengthlogAPI.jpa.UserRepository;

@RestController
public class UserController {
	private UserRepository repository;
	private UserServiceImpl service;

	public UserController(UserRepository repository, UserServiceImpl service) {
		super();
		this.repository = repository;
		this.service = service;
	}
	
	@GetMapping("/api/1.0/users")
	public List<User> getAll() {
		return service.allUsers();
	}
	
	@GetMapping("/api/1.0/users/{id}")
	public Optional<User> getUserById(@PathVariable Integer id) {
		return repository.findById(id);
	}
	
	@PostMapping("/api/1.0/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = service.create(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/api/1.0/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		service.delete(id);
	}
	
	@PutMapping("/api/1.0/users/{id}") 
	public User updateUser(@PathVariable Integer id, @RequestBody User user) {
		return service.update(id, user);
	}

}
