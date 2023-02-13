package com.mac718.strengthlogAPI.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WorkSet implements Comparable<WorkSet> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String movement;
	private Double weight;
	private Integer reps;
	private Double rpe;
	private Integer setOrder;
	private Double estimatedMax;

//	public WorkSet(String movement, Double weight, Integer reps, Double rpe, Integer setOrder, Double estimatedMax) {
//		super();
//		this.movement = movement;
//		this.weight = weight;
//		this.reps = reps;
//		this.rpe = rpe;
//		this.setOrder = setOrder;
//		this.estimatedMax = estimatedMax;
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public Double getWeight() {
//		return weight;
//	}
//
//	public void setWeight(Double weight) {
//		this.weight = weight;
//	}
//
//	public String getMovement() {
//		return movement;
//	}
//
//	public void setMovement(String movement) {
//		this.movement = movement;
//	}
//
//	public Integer getReps() {
//		return reps;
//	}
//
//	public void setReps(Integer reps) {
//		this.reps = reps;
//	}
//
//	public Double getRpe() {
//		return rpe;
//	}
//
//	public void setRpe(Double rpe) {
//		this.rpe = rpe;
//	}
//
//	public Integer getSetOrder() {
//		return setOrder;
//	}
//
//	public void setSetOrder(Integer setOrder) {
//		this.setOrder = setOrder;
//	}
//
//	public Double getEstimatedMax() {
//		return estimatedMax;
//	}
//
//	public void setEstimatedMax(Double estimatedMax) {
//		this.estimatedMax = estimatedMax;
//	}

	@Override
	public String toString() {
		return "WorkSet [id=" + id + ", movement=" + movement + ", weight=" + weight + ", reps=" + reps + ", rpe=" + rpe
				+ ", setOrder=" + setOrder + ", estimatedMax=" + estimatedMax + "]";
	}

	@Override
	public int compareTo(WorkSet o) {
		if (this.getEstimatedMax() > o.getEstimatedMax()) {
			return 1;
		} else if (this.getEstimatedMax() < o.getEstimatedMax()) {
			return -1;
		} else {
			return 0;
		}
		
	}

}
