package com.mac718.strengthlogAPI.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mac718.strengthlogAPI.jpa.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	private UserRepository repository;
	
	public UserServiceImpl(UserRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<User> allUsers() {
		return repository.findAll();
	}
	
	@Override
	public User create(User user) {
		return repository.save(user);
	}
	
	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	@Override
	public User update(Integer id, User user) {
		var userToUpdate = repository.findById(id).get();

		userToUpdate.setName(user.getName());
		userToUpdate.setEmail(user.getEmail());
		userToUpdate.setSex(user.getSex());
		userToUpdate.setBodyWeight(user.getBodyWeight());
		userToUpdate.setAge(user.getAge());
		
		return repository.save(userToUpdate);
		
	}

}
