package com.antoniorg.spaceship.application.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private String secret = "MiSecretKey";

	public String generateToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 1))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean validateToken(String token, String username) {
		var tokenUsername = extractUsername(token);
		
		return (username.equals(tokenUsername) && !isTokenExpired(token));
	}

	public String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}

	protected Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	protected boolean isTokenExpired(String token) {
		return extractAllClaims(token).getExpiration().before(new Date());
	}
}
