package com.mac718.strengthlogAPI.user;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {
	public abstract List<UserEntity> allUsers();
	public abstract Optional<UserEntity> userById(Integer id);
	public abstract void delete(Integer id);
	public abstract UserEntity update(Integer id, UserEntity user);
	public abstract Workout createWorkout(Integer userId, Workout workout) throws Exception;
	public abstract List<Workout> workoutsByMonth(Integer userId, LocalDate start, LocalDate end);
	public abstract List<PersonalRecord> PrsByMovement(String movement, Integer user_id);
	public abstract void deleteWorkout(Integer id);
}
