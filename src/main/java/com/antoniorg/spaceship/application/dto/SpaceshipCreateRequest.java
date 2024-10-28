package com.antoniorg.spaceship.application.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SpaceshipCreateRequest {
	
	@NotBlank(message = "{validation.spaceship.name.notblank}")
	private String spaceshipName;
	
	@NotBlank(message = "{validation.spaceship.moviename.notblank}")
	private String movieName;
	
	@NotNull(message = "{validation.spaceship.movieyear.notnull}")
	private Integer movieYear;
}
