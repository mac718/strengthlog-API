package com.mac718.strengthlogAPI.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mac718.strengthlogAPI.jpa.MovementRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MovementServiceImpl implements MovementService{
	
	private final MovementRepository movementRepository;

	@Override
	public List<Movement> getMovementsByCategory(String category) {
		return movementRepository.findByCategory(category);
	}

}
