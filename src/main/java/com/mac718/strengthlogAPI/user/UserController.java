package com.mac718.strengthlogAPI.user;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mac718.strengthlogAPI.jwt.JwtService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	private UserServiceImpl service;
	//private JwtService jwtService;

	public UserController(UserServiceImpl service, JwtService jwtService) {
		super();
		this.service = service;
		//this.jwtService = jwtService;
	}
	
	@GetMapping
	public ResponseEntity<List<UserEntity>> getAll() {
		return new ResponseEntity<List<UserEntity>>(service.allUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<UserEntity>> getUserById(@PathVariable Integer id) {
		return new ResponseEntity<Optional<UserEntity>>(service.userById(id), HttpStatus.OK);
	}
	
//	@PostMapping
//	public ResponseEntity<User> createUser(@RequestBody User user) {
//		User savedUser = jwtService.register(user);
//		
//		URI location = ServletUriComponentsBuilder
//				.fromCurrentRequest()
//				.path("/{id}")
//				.buildAndExpand(savedUser.getId())
//				.toUri();
//		
//		return ResponseEntity.created(location).build();
//	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Integer id) {
		service.delete(id);
	}
	
	@PutMapping("/{id}") 
	public ResponseEntity<UserEntity> updateUser(@PathVariable Integer id, @RequestBody UserEntity user) {
		return new ResponseEntity<UserEntity>(service.update(id, user), HttpStatus.OK);
	}
	
	
	@PostMapping("/{id}/workouts")
	public ResponseEntity<Workout> createWorkoutForUser(@PathVariable Integer id, @RequestBody Workout workout) throws Exception {
				
		Workout savedWorkout = service.createWorkout(id, workout);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedWorkout.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("{userId}/workouts/{year}/{month}")
	public ResponseEntity<List<Workout>> getWorkoutsByMonth(@PathVariable Integer userId, @PathVariable Integer year, @PathVariable Integer month)  {
		LocalDate start = LocalDate.of(year, month, 1);
		Integer numberOfDays = start.lengthOfMonth();
		LocalDate end = LocalDate.of(year, month, numberOfDays);
		
		return new ResponseEntity<List<Workout>>(service.workoutsByMonth(userId, start, end), HttpStatus.OK);
	}
	
	@DeleteMapping("{userId}/workouts/{workoutId}")
	public void deleteWorkoutById(@PathVariable Integer userId, @PathVariable Integer workoutId) {
		service.deleteWorkout(workoutId);
	}

}
