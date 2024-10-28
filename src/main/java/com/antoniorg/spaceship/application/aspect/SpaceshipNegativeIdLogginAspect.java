package com.antoniorg.spaceship.application.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class SpaceshipNegativeIdLogginAspect {
	
	@Before("execution(* com.antoniorg.spaceship.web.controller.impl.SpaceshipRestController.getSpaceshipById(..)) && args(id,..)")
	public boolean logIfIdIsNegative(JoinPoint joinPoint, Long id) {
		if (id < 0) {
			log.warn("Request for spaceship with negative ID: {}", id);
			return true;
		}
		
		return false;
	}
}