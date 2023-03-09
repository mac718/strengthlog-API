package com.mac718.strengthlogAPI.user;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

interface UserService {
	List<UserEntity> allUsers();
	Optional<UserEntity> userById(Integer id);
	void delete(Integer id);
	UserEntity update(Integer id, UserEntity user);
	Workout createWorkout(Integer userId, Workout workout) throws Exception;
	List<Workout> workoutsByMonth(Integer userId, LocalDate start, LocalDate end);
	List<PersonalRecord> PrsByMovement(String movement, Integer user_id);
	List<PersonalRecord> RecentPrs(Integer user_id);
	void deleteWorkout(Integer id);
}
