package com.mac718.strengthlogAPI.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mac718.strengthlogAPI.user.Movement;

public interface MovementRepository extends JpaRepository<Movement, Integer>{

}
