package com.antoniorg.spaceship.application.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

@ExtendWith(MockitoExtension.class)
class JwtRequestFilterTest {

    private @Mock JwtUtil jwtUtil;
    private @Mock FilterChain filterChain;
    private @InjectMocks JwtRequestFilter jwtRequestFilter;

    @Test
    void testDoFilterInternal_WithValidJwt() throws ServletException, IOException {
        var request = new MockHttpServletRequest();
        var response = new MockHttpServletResponse();
        var username = "testUser";
        var jwt = "valid-jwt-token";

        request.addHeader("Authorization", "Bearer " + jwt);

        when(jwtUtil.extractUsername(jwt)).thenReturn(username);
        when(jwtUtil.validateToken(jwt, username)).thenReturn(true);

        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        assertNotNull(authentication);
        assertEquals(username, authentication.getPrincipal());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void testDoFilterInternal_WithInvalidJwt() throws ServletException, IOException {
        var request = new MockHttpServletRequest();
        var response = new MockHttpServletResponse();
        var jwt = "invalid-jwt-token";

        request.addHeader("Authorization", "Bearer " + jwt);

        when(jwtUtil.extractUsername(jwt)).thenReturn(null);

        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        assertNull(authentication);
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void testDoFilterInternal_NoAuthorizationHeader() throws ServletException, IOException {
        var request = new MockHttpServletRequest();
        var response = new MockHttpServletResponse();

        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        assertNull(authentication);
        verify(filterChain).doFilter(request, response);
    }
}
