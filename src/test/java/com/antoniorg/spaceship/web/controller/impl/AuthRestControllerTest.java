package com.antoniorg.spaceship.web.controller.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import com.antoniorg.spaceship.application.dto.AuthRequest;
import com.antoniorg.spaceship.application.security.JwtUtil;

@ExtendWith(MockitoExtension.class)
class AuthRestControllerTest {

	private @Mock JwtUtil jwtUtil;
	private @Mock AuthenticationManager authenticationManager;
	private @InjectMocks AuthRestController authRestController;

	@Test
	void testCreateAuthenticationToken() throws AuthenticationException {
		var authRequest = new AuthRequest("testUser", "testPassword");
		var token = "generated-jwt-token";

		when(jwtUtil.generateToken("testUser")).thenReturn(token);

		var result = authRestController.createAuthenticationToken(authRequest);

		verify(authenticationManager).authenticate(new UsernamePasswordAuthenticationToken("testUser", "testPassword"));
		assertEquals(token, result);
	}

	@Test
	void testCreateAuthenticationTokenThrowsException() {
		var authRequest = new AuthRequest("testUser", "wrongPassword");

		doThrow(new BadCredentialsException("Invalid credentials")).when(authenticationManager)
				.authenticate(new UsernamePasswordAuthenticationToken("testUser", "wrongPassword"));

		assertThrows(AuthenticationException.class, () -> authRestController.createAuthenticationToken(authRequest));
	}
}