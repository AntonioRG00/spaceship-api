package com.antoniorg.spaceship.application.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.antoniorg.spaceship.domain.model.UserModel;
import com.antoniorg.spaceship.infrastructure.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class MyUserDetailsServiceImplTest {

    private @Mock UserRepository userRepository;
    private @InjectMocks MyUserDetailsServiceImpl myUserDetailsService;

    @Test
    void testLoadUserByUsername_UserFound() {
        var username = "testUser";
        var user = new UserModel();
        user.setUsername(username);
        user.setPwd("testPassword");

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        var userDetails = myUserDetailsService.loadUserByUsername(username);

        assertEquals(username, userDetails.getUsername());
        assertEquals("testPassword", userDetails.getPassword());
        assertEquals(0, userDetails.getAuthorities().size());
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        var username = "nonexistentUser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        
        assertThrows(UsernameNotFoundException.class, () -> myUserDetailsService.loadUserByUsername(username));
    }
}
