package com.mac718.strengthlogAPI.user;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mac718.strengthlogAPI.jpa.UserRepository;
import com.mac718.strengthlogAPI.jpa.WorkoutRepository;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	private WorkoutRepository workoutRepository;

	public UserServiceImpl(UserRepository userRepository, WorkoutRepository workoutRepository) {
		super();
		this.userRepository = userRepository;
		this.workoutRepository = workoutRepository;
	}

	@Override
	public List<User> allUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public Optional<User> userById(Integer id) { 
		Optional<User> user = userRepository.findById(id);
		
		if (user.isEmpty()) {
			throw new UserNotFoundException("id" + id);
		}
		
		return user;
	}
	
	@Override
	public User create(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public void delete(Integer id) {
		userRepository.deleteById(id);
	}
	
	@Override
	public User update(Integer id, User user) {
		var userToUpdate = userRepository.findById(id).get();

		userToUpdate.setName(user.getName());
		userToUpdate.setEmail(user.getEmail());
		userToUpdate.setSex(user.getSex());
		userToUpdate.setBodyWeight(user.getBodyWeight());
		userToUpdate.setAge(user.getAge());
		
		return userRepository.save(userToUpdate);
		
	}

	@Override
	public Workout createWorkout(Integer userId,  Workout workout) throws Exception {
		
		Optional<User> user = userRepository.findById(userId);;
		if (user.isEmpty()) {
			throw new UserNotFoundException("id" + userId);
		}
		
		workout.setUserId(user.get());
		
		Workout newWorkout = workoutRepository.save(workout);

		
		return newWorkout;
		
	}

	@Override
	public List<Workout> workoutsByMonth(Integer userId, LocalDate start, LocalDate end) {
		return workoutRepository.getWorkoutsByMonth(userId, start, end);
	}	
	
	

}
