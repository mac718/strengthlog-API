package com.mac718.strengthlogAPI.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Entity
public class Movement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String category;
	
	public Movement(String name, String category) {
		super();
		this.name = name;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	@Override
	public String toString() {
		return "Movement [name=" + name + ", category=" + category + "]";
	}

}
