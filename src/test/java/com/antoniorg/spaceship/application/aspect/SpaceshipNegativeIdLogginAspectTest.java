package com.antoniorg.spaceship.application.aspect;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SpaceshipNegativeIdLogginAspectTest {

    private @Mock JoinPoint joinPoint;
    private @InjectMocks SpaceshipNegativeIdLogginAspect aspect;

    @Test
    void testLogIfIdIsNegative() {
        Long negativeId = -1L;

        boolean result = aspect.logIfIdIsNegative(joinPoint, negativeId);

        assertTrue(result, "Expected logIfIdIsNegative to return true for negative ID");
    }

    @Test
    void testLogIfIdIsPositive() {
        var positiveId = 1L;

        boolean result = aspect.logIfIdIsNegative(joinPoint, positiveId);

        assertFalse(result, "Expected logIfIdIsNegative to return false for positive ID");
    }
}