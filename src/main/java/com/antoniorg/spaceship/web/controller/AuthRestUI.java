package com.antoniorg.spaceship.web.controller;

import javax.validation.Valid;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;

import com.antoniorg.spaceship.application.dto.AuthRequest;

import io.swagger.v3.oas.annotations.Operation;

public interface AuthRestUI {

	@Operation(summary = "Iniciar sesión y obtener un token JWT")
	public String createAuthenticationToken(@RequestBody @Valid AuthRequest authRequest) throws AuthenticationException;
}
