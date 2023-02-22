package com.mac718.strengthlogAPI.workout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mac718.strengthlogAPI.jpa.WorkoutRepository;
import com.mac718.strengthlogAPI.user.Workout;

@Service
public class WorkoutServiceImpl implements WorkoutService{
	@Autowired
	WorkoutRepository workoutRepository;
	
	@Override
	public Workout getWorkoutById(Integer id) {
		return workoutRepository.findById(id).orElseThrow();
	}

}
