package com.antoniorg.spaceship.application.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class SpaceshipResponse implements Serializable {
	private static final long serialVersionUID = -5721670796219647313L;
	
	private Long id;
	private String spaceshipName;
	private String movieName;
	private Integer movieYear;
}
