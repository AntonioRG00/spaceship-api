package com.antoniorg.spaceship.web.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antoniorg.spaceship.application.dto.AuthRequest;
import com.antoniorg.spaceship.application.security.JwtUtil;
import com.antoniorg.spaceship.web.controller.AuthRestUI;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "AuthAPI", description = "API-RESTful para obtener el token de seguridad")
public class AuthRestController implements AuthRestUI {

	private @Autowired JwtUtil jwtUtil;
	private @Autowired AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public String createAuthenticationToken(AuthRequest authRequest) throws AuthenticationException {
		var userAndPwd = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPwd());
		authenticationManager.authenticate(userAndPwd);

		return jwtUtil.generateToken(authRequest.getUsername());
	}
}
