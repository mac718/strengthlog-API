package com.mac718.strengthlogAPI.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mac718.strengthlogAPI.jpa.PersonalRecordRepository;
import com.mac718.strengthlogAPI.jpa.UserRepository;
import com.mac718.strengthlogAPI.jpa.WorkoutRepository;
import com.mac718.strengthlogAPI.staticdata.RpeChart;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	private WorkoutRepository workoutRepository;
	private PersonalRecordRepository personalRecordRepository;

	public UserServiceImpl(UserRepository userRepository, WorkoutRepository workoutRepository, PersonalRecordRepository personalRecordRepository) {
		super();
		this.userRepository = userRepository;
		this.workoutRepository = workoutRepository;
		this.personalRecordRepository = personalRecordRepository;
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
		
		for (WorkSet set : workout.getSets()) {
			Double percentage = RpeChart.rpeChart.get(set.getRpe())[set.getReps() - 1][1];
			Double multiplier = 100.0 / percentage;
			set.setEstimatedMax(set.getWeight() * multiplier);
		}
		
		workout.setUserId(user.get());
		
		Workout newWorkout = workoutRepository.save(workout);
		
		
		List<String> workoutMovements = newWorkout.getSets().stream().map(workSet -> workSet.getMovement()).toList();
		
		// check sets for each movement of workout; if any set's estimated max is higher than the current prs
		// estimated max or if no pr exists for the movement, create a new pr with the higher estimated max.
		for (String movement : workoutMovements) {
			List<PersonalRecord> prs = personalRecordRepository.getUserPrsByMovement(movement, userId);
			PersonalRecord latest;
			if (prs.size() > 0) {
				latest = prs.get(prs.size() - 1);
			} else {
				latest = new PersonalRecord();
			}
			
			List<WorkSet> currentWorkoutSetsForMovement = newWorkout.getSets().stream()
					.filter(workSet -> workSet.getMovement().equals(movement)).toList();
			
			List<WorkSet> currentWorkoutSetsForMovementList = new ArrayList<>(currentWorkoutSetsForMovement);
			
			
			
			Collections.sort(currentWorkoutSetsForMovementList, Collections.reverseOrder());
			System.out.println("pr " + currentWorkoutSetsForMovementList);
			
			
			if (prs.size() == 0 ||  currentWorkoutSetsForMovementList.get(0).getEstimatedMax() > latest.getEstimatedMax()) {
				PersonalRecord newPr = new PersonalRecord(movement, newWorkout.getDate(), 
						currentWorkoutSetsForMovementList.get(0).getEstimatedMax());
				
				
				newPr.setUser(user.get());
				newPr.setWorkout(newWorkout);
				personalRecordRepository.save(newPr);
			}
			
			
			
		}
		
		return newWorkout;
		
	}

	@Override
	public List<Workout> workoutsByMonth(Integer userId, LocalDate start, LocalDate end) {
		return workoutRepository.getWorkoutsByMonth(userId, start, end);
	}

	@Override
	public List<PersonalRecord> PrsByMovement(String movement, Integer user_id) {
		return personalRecordRepository.getUserPrsByMovement(movement, user_id);
	}

	@Override
	public void deleteWorkout(Integer id) {
		workoutRepository.deleteById(id);		
	}
	
	

}
