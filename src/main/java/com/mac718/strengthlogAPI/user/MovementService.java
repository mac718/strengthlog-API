package com.mac718.strengthlogAPI.user;

import java.util.List;

public interface MovementService {
	List<Movement> getMovementsByCategory(String category);
}
