package com.antoniorg.spaceship.application.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.antoniorg.spaceship.application.dto.SpaceshipCreateRequest;
import com.antoniorg.spaceship.application.dto.SpaceshipResponse;
import com.antoniorg.spaceship.application.dto.SpaceshipUpdateRequest;

public interface SpaceshipService {

	@Cacheable(value = "spaceship-cache")
	public Page<SpaceshipResponse> getAllSpaceships(Pageable pageable);
	
	@Cacheable(value = "spaceship-cache", key = "#id")
	public SpaceshipResponse getSpaceshipById(Long id);
	
	@Cacheable(value = "spaceship-cache", key = "#name")
	public List<SpaceshipResponse> getSpaceshipsByName(String name);
	
	@CacheEvict(value = "spaceship-cache", allEntries = true)
	public SpaceshipResponse createSpaceship(SpaceshipCreateRequest spaceship);
	
	@CacheEvict(value = "spaceship-cache", key = "#id")
	public SpaceshipResponse updateSpaceship(Long id, SpaceshipUpdateRequest req);
	
	@CacheEvict(value = "spaceship-cache", key = "#id")
	public void deleteSpaceship(Long id);
}
