package com.antoniorg.spaceship.application.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequest {
	private @NotBlank(message = "{validation.user.username.notblank}") String username;
	private @NotBlank(message = "{validation.user.pwd.notblank}") String pwd;
}
