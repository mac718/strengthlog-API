package com.mac718.strengthlogAPI.user;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Workout {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Integer id;
	private final LocalDate date;
	//@OneToMany
	private List<String> movements;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name="workout_id")
	private List<WorkSet> sets;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	public void setUserId(UserEntity user) {
		this.user = user;
	}	

}
