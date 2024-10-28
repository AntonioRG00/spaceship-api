package com.antoniorg.spaceship.application.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.antoniorg.spaceship.application.security.JwtRequestFilter;

@SpringBootTest
@AutoConfigureMockMvc
@Import(SecurityConfig.class)
@ActiveProfiles("test")
class SecurityConfigTest {

	private @Autowired MockMvc mockMvc;
	private @Mock JwtRequestFilter jwtRequestFilter;
	private @Autowired PasswordEncoder passwordEncoder;

	@Test
	void testProtectedEndpointsRequireAuthentication() throws Exception {
		mockMvc.perform(get("/spaceship")).andExpect(status().isForbidden());
	}

	@Test
	void testPasswordEncoderBean() {
		var password = "testPassword";
		var encodedPassword = passwordEncoder.encode(password);

		assertNotNull(encodedPassword);
		assertTrue(passwordEncoder.matches(password, encodedPassword));
	}
}