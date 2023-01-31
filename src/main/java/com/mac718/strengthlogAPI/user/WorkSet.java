package com.mac718.strengthlogAPI.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	//private Integer order;

//	@ManyToOne
//	@JsonIgnore
//	private Workout workout;
	
	public WorkSet() {}

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

//	public Workout getWorkout() {
//		return workout;
//	}
//
//	public void setWorkout(Workout workout) {
//		this.workout = workout;
//	}

//	public Integer getOrder() {
//		return order;
//	}
//
//	public void setOrder(Integer order) {
//		this.order = order;
//	}
//
//	@Override
//	public String toString() {
//		return "WorkSet [id=" + id + ", movement=" + movement + ", weight=" + weight + ", reps=" + reps + ", rpe=" + rpe
//				+ ", order=" + order + ", workout=" + workout + "]";
//	}

	

}
