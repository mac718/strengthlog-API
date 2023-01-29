package com.mac718.strengthlogAPI.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class WorkSet {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String movement;
	private Double weight;
	private Integer reps;
	private Double rpe;

	@ManyToOne
	private Workout workout;

	public WorkSet(String movement, Double weight, Integer reps, Double rpe) {
		super();
		this.movement = movement;
		this.weight = weight;
		this.reps = reps;
		this.rpe = rpe;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getMovement() {
		return movement;
	}

	public void setMovement(String movement) {
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
		return "Set [id=" + id + ", movement=" + movement + ", weight=" + weight + ", reps=" + reps + ", rpe=" + rpe
				+ ", workout=" + workout + "]";
	}

	

}
