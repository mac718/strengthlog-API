package com.mac718.strengthlogAPI;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mac718.strengthlogAPI.jpa.UserRepository;
import com.mac718.strengthlogAPI.user.UserEntity;
import com.mac718.strengthlogAPI.user.UserNotFoundException;
import com.mac718.strengthlogAPI.user.UserService;

@SpringBootTest
public class UserServiceImplTest {
	@Autowired
	private UserService service;
	
	@MockBean
	private UserRepository repository;
	
	UserEntity user_1 = UserEntity.builder()
			.email("mike@mike.com")
			.password("password")
			.build();

	UserEntity user_2 = UserEntity.builder()
			.email("john.com")
			.password("otherPassword")
			.build();
	
	
	@Test
	public void allUsersTest() {
		
		when(repository.findAll()).thenReturn(List.of(user_1, user_2));
	
		assertEquals(service.allUsers().size(), 2);
		assertEquals(service.allUsers().get(0).getEmail(), "mike@mike.com");
	}
	
	@Test
	public void userByIdTest() {
		when(repository.findById(1)).thenReturn(Optional.of(user_1));
		assertEquals(service.userById(1).get().getEmail(), "mike@mike.com");
		
		when(repository.findById(1)).thenReturn(Optional.empty());
		Exception exception = assertThrows(UserNotFoundException.class, () -> service.userById(1));
		assertEquals("id1", exception.getMessage());
	}
//	
//	@Test
//	public void createTest() {
//		User user = new User(1, "Mike", "mike@mike.com", "m", 190.0, LocalDate.of(1980, 10, 21));
//		when(repository.save(user)).thenReturn(user);
//		assertEquals(service.create(user), user);
//	}
//	
//	@Test
//	public void deleteTest() {
//		User user = new User(1, "Mike", "mike@mike.com", "m", 190.0, LocalDate.of(1980, 10, 21));
//		service.delete(user.getId());
//		verify(repository, times(1)).deleteById(user.getId());
//	}
	

}
