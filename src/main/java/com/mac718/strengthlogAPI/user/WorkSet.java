package com.mac718.strengthlogAPI.user;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class WorkSet {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String movement;
	private Double weight;
	private Integer reps;
	private Double rpe;
	private Integer setOrder;
	
	public WorkSet() {}

	public WorkSet(String movement, Double weight, Integer reps, Double rpe, Integer setOrder) {
		super();
		this.movement = movement;
		this.weight = weight;
		this.reps = reps;
		this.rpe = rpe;
		this.setOrder = setOrder;
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

	public Integer getSetOrder() {
		return setOrder;
	}

	public void setSetOrder(Integer setOrder) {
		this.setOrder = setOrder;
	}

	@Override
	public String toString() {
		return "WorkSet [id=" + id + ", movement=" + movement + ", weight=" + weight + ", reps=" + reps + ", rpe=" + rpe
				+ ", setOrder=" + setOrder + "]";
	}

	

}
