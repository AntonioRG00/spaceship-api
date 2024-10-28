package com.antoniorg.spaceship.web.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antoniorg.spaceship.application.dto.SpaceshipCreateRequest;
import com.antoniorg.spaceship.application.dto.SpaceshipResponse;
import com.antoniorg.spaceship.application.dto.SpaceshipUpdateRequest;
import com.antoniorg.spaceship.application.service.impl.SpaceshipServiceImpl;
import com.antoniorg.spaceship.infrastructure.kafka.SpaceshipProducer;
import com.antoniorg.spaceship.web.controller.SpaceshipRestUI;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/spaceship")
@Tag(name = "SpaceshipAPI", description = "API-RESTful para gestionar naves espaciales de películas y series")
public class SpaceshipRestController implements SpaceshipRestUI {

	private @Autowired SpaceshipServiceImpl spaceshipService;
	private @Autowired SpaceshipProducer spaceshipProducer;

	@GetMapping
	public ResponseEntity<Page<SpaceshipResponse>> getAllSpaceships(int page, int size, String sortBy, Direction sortDirection) {
		var spaceships = spaceshipService.getAllSpaceships(PageRequest.of(page, size, Sort.by(sortDirection, sortBy)));

		return ResponseEntity.ok(spaceships);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SpaceshipResponse> getSpaceshipById(Long id) {
		var spaceship = spaceshipService.getSpaceshipById(id);

		return ResponseEntity.ok(spaceship);
	}

	@GetMapping("/name-search")
	public ResponseEntity<List<SpaceshipResponse>> getSpaceshipsByName(String name) {
		var spaceships = spaceshipService.getSpaceshipsByName(name);

		return ResponseEntity.ok(spaceships);
	}

	@PostMapping
	public ResponseEntity<SpaceshipResponse> createSpaceship(SpaceshipCreateRequest req) {
		var newSpaceship = spaceshipService.createSpaceship(req);
		
		spaceshipProducer.sendSpaceshipCreatedEvent(newSpaceship);

		return ResponseEntity.status(HttpStatus.CREATED).body(newSpaceship);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SpaceshipResponse> updateSpaceship(Long id, SpaceshipUpdateRequest req) {
		var spaceship = spaceshipService.updateSpaceship(id, req);

		return ResponseEntity.ok(spaceship);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSpaceship(Long id) {
		spaceshipService.deleteSpaceship(id);

		return ResponseEntity.noContent().build();
	}
}
