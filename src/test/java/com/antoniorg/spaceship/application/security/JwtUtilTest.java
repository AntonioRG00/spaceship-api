package com.antoniorg.spaceship.application.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.test.util.ReflectionTestUtils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

class JwtUtilTest {

	private @InjectMocks JwtUtil jwtUtil;

	private String secret = "MiSecretKey";
	private String username = "testUser";
	private String token;

	@BeforeEach
	void setUp() {
		jwtUtil = new JwtUtil();
		ReflectionTestUtils.setField(jwtUtil, "secret", secret);
		token = jwtUtil.generateToken(username);
	}

	@Test
	void testGenerateToken() {
		assertNotNull(token);
		assertEquals(username, jwtUtil.extractUsername(token));
	}

	@Test
	void testValidateToken_ValidToken() {
		assertTrue(jwtUtil.validateToken(token, username));
	}

	@Test
	void testValidateToken_InvalidUsername() {
		assertFalse(jwtUtil.validateToken(token, "invalidUser"));
	}

	@Test
	void testValidateToken_ExpiredToken() {
		var expiredToken = Jwts.builder().setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 2))
				.setExpiration(new Date(System.currentTimeMillis() - 1000 * 60 * 60))
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, secret).compact();

		assertThrows(ExpiredJwtException.class, () -> jwtUtil.validateToken(expiredToken, username));
	}

	@Test
	void testExtractUsername() {
		assertEquals(username, jwtUtil.extractUsername(token));
	}

	@Test
	void testIsTokenExpired_NotExpired() {
		assertFalse(jwtUtil.isTokenExpired(token));
	}

	@Test
	void testIsTokenExpired_ExpiredToken() {
		var expiredToken = Jwts.builder().setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 2))
				.setExpiration(new Date(System.currentTimeMillis() - 1000 * 60 * 60))
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, secret).compact();

		assertThrows(ExpiredJwtException.class, () -> jwtUtil.isTokenExpired(expiredToken));
	}
}
