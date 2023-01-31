package com.mac718.strengthlogAPI.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.mac718.strengthlogAPI.jpa.SetRepository;
import com.mac718.strengthlogAPI.jpa.UserRepository;
import com.mac718.strengthlogAPI.jpa.WorkoutRepository;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	private WorkoutRepository workoutRepository;
	private SetRepository setRepository;
	
	public UserServiceImpl(UserRepository userRepository, WorkoutRepository workoutRepository, SetRepository setRepository) {
		super();
		this.userRepository = userRepository;
		this.workoutRepository = workoutRepository;
		this.setRepository = setRepository;
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
		//Optional<Workout> savedWorkout = workoutRepository.findById(newWorkout.getId());
		
		List<WorkSet> newSets = new ArrayList<>();

//		for (WorkSet set:newWorkout.getSets()) {
//			WorkSet newSet = new WorkSet(set.getMovement(), set.getWeight(), set.getReps(), set.getRpe());
//			//newSet.setWorkout(savedWorkout.get());
//			WorkSet savedSet = setRepository.save(newSet);
//			newSets.add(savedSet);
//		}
		
		//newWorkout.setSets(newSets);

		
		return newWorkout;
		
	}

	@Override
	public List<Workout> workoutsByMonth(Integer userId, LocalDate start, LocalDate end) {
		
//		@Query("SELECT w FROM Workout w WHERE user_id = userId")
//		List<Workout> workouts = workoutRepository.findAll(null, null)
		return workoutRepository.getWorkoutsByMonth(userId, start, end);
	}	
	
	

}
