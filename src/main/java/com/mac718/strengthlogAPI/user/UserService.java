package com.mac718.strengthlogAPI.user;

import java.util.List;

public interface UserService {
	public abstract List<User> allUsers();
	public abstract User create(User user);
	public abstract void delete(Integer id);
	public abstract User update(Integer id, User user);
	public abstract Workout createWorkout(Integer userId, Workout workout) throws Exception;
}
