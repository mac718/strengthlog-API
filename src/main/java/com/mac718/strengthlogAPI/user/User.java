package com.mac718.strengthlogAPI.user;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name="user_details")
public class User {
	protected User() {
		
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	private String email;
	private String sex;
	private Double bodyWeight;
	private LocalDate age;
	
	@OneToMany(mappedBy="user")
	private List<Workout> workouts;
	
	public User(Integer id, String name, String email, String sex, Double bodyWeight, LocalDate age) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.sex = sex;
		this.bodyWeight = bodyWeight;
		this.age = age;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Double getBodyWeight() {
		return bodyWeight;
	}

	public void setBodyWeight(Double bodyWeight) {
		this.bodyWeight = bodyWeight;
	}
	
	public LocalDate getAge() {
		return age;
	}

	public void setAge(LocalDate age) {
		this.age = age;
	}
	
	
	
}
