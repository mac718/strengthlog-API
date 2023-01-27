package com.mac718.strengthlogAPI.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Set {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Movement movement;
	private Integer reps;
	private Double rpe;

	@ManyToOne
	private Workout workout;

	public Set(Movement movement, Integer reps, Double rpe) {
		super();
		this.movement = movement;
		this.reps = reps;
		this.rpe = rpe;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Movement getMovement() {
		return movement;
	}

	public void setMovement(Movement movement) {
		this.movement = movement;
	}

	public Integer getReps() {
		return reps;
	}

	public void setReps(Integer reps) {
		this.reps = reps;
	}

	public Double getRpe() {
		return rpe;
	}

	public void setRpe(Double rpe) {
		this.rpe = rpe;
	}

	public Workout getWorkout() {
		return workout;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}

	@Override
	public String toString() {
		return "Set [reps=" + reps + ", rpe=" + rpe + "]";
	}

}
