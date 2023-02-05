package com.mac718.strengthlogAPI.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mac718.strengthlogAPI.user.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Integer>{

}
