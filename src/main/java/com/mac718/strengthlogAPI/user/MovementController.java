package com.mac718.strengthlogAPI.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movements")
public class MovementController {
	private final MovementService movementService;
	
	@GetMapping("/{category}")
	public ResponseEntity<List<Movement>> getMovementsByCategory(@PathVariable String category) {
		return new ResponseEntity<List<Movement>>(movementService.getMovementsByCategory(category), HttpStatus.OK);
	}

}
