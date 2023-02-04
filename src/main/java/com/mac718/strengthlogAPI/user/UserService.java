package com.mac718.strengthlogAPI.user;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {
	public abstract List<User> allUsers();
	public abstract Optional<User> userById(Integer id);
	public abstract User create(User user);
	public abstract void delete(Integer id);
	public abstract User update(Integer id, User user);
	public abstract Workout createWorkout(Integer userId, Workout workout) throws Exception;
	public abstract List<Workout> workoutsByMonth(Integer userId, LocalDate start, LocalDate end);
	public abstract List<PersonalRecord> PrsByMovement(String movement, Integer user_id);
}
