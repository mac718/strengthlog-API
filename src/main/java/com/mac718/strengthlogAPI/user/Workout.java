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

@Entity
public class Workout {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private LocalDate date;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name="workout_id")
	private List<WorkSet> sets;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	protected Workout() {}

	public Workout(LocalDate date, List<WorkSet> sets, User user) {
		super();
		this.date = date;
		this.sets = sets;
		this.user= user;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<WorkSet> getSets() {
		return sets;
	}

	public void setSets(List<WorkSet> sets) {
		this.sets = sets;
	}
	

	public User getUser() {
		return user;
	}

	public void setUserId(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Workout [id=" + id + ", date=" + date + ", sets=" + sets + ", user=" + user + "]";
	}

	

	
	
	

}
