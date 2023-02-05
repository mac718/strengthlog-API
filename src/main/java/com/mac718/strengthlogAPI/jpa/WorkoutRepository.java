package com.mac718.strengthlogAPI.jpa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mac718.strengthlogAPI.user.Workout;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Integer>{
	@Query(value="SELECT * from Workout WHERE user_id = ? and date BETWEEN ? AND ?", nativeQuery=true)
	List<Workout> getWorkoutsByMonth(Integer userId, LocalDate start, LocalDate end);
}
