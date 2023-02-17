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
