package com.antoniorg.spaceship.application.dto;

import lombok.Data;

@Data
public class SpaceshipUpdateRequest {
	private String spaceshipName;
	private String movieName;
	private Integer movieYear;
}
