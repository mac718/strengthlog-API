package com.mac718.strengthlogAPI.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mac718.strengthlogAPI.user.WorkSet;

public interface SetRepository extends JpaRepository<WorkSet, Integer>{

}
