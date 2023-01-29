package com.mac718.strengthlogAPI.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mac718.strengthlogAPI.user.Workout;

public interface WorkoutRepository extends JpaRepository<Workout, Integer>{
	
}
