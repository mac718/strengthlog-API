package com.mac718.strengthlogAPI.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mac718.strengthlogAPI.user.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
