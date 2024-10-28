package com.antoniorg.spaceship.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "SPACESHIP")
public class SpaceshipModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "SPACESHIP_NAME", nullable = false, length = 255)
	private String spaceshipName;

	@Column(name = "MOVIE_NAME", nullable = false, length = 255)
	private String movieName;

	@Column(name = "MOVIE_YEAR", nullable = false)
	private Integer movieYear;
}
