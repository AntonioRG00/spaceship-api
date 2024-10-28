package com.antoniorg.spaceship.web.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import com.antoniorg.spaceship.application.exception.ItemNotFoundException;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
    private WebRequest webRequest;

    @BeforeEach
    void setUp() {
        webRequest = mock(WebRequest.class);
    }

    @Test
    void testHandleGlobalException() {
        var ex = new Exception("Test exception");
        var response = globalExceptionHandler.handleGlobalException(ex, webRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred", response.getBody().get("message"));
        assertEquals("Test exception", response.getBody().get("details"));
    }

    @Test
    void testHandleIllegalArgumentException() {
        var ex = new IllegalArgumentException("Invalid argument");

        var response = globalExceptionHandler.handleIllegalArgumentException(ex, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid argument", response.getBody().get("message"));
    }

    @Test
    void testHandleItemNotFoundException() {
        var ex = new ItemNotFoundException("Spaceship", 1L);

        var response = globalExceptionHandler.handleIItemNotFoundException(ex, webRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Spaceship with ID 1 was not found", response.getBody().get("message"));
    }
}