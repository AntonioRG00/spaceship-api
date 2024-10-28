package com.antoniorg.spaceship.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.antoniorg.spaceship.application.dto.SpaceshipCreateRequest;
import com.antoniorg.spaceship.application.dto.SpaceshipResponse;
import com.antoniorg.spaceship.application.dto.SpaceshipUpdateRequest;
import com.antoniorg.spaceship.application.exception.ItemNotFoundException;
import com.antoniorg.spaceship.application.mapper.SpaceshipMapper;
import com.antoniorg.spaceship.application.service.SpaceshipService;
import com.antoniorg.spaceship.infrastructure.repository.SpaceshipRepository;

@Service
public class SpaceshipServiceImpl implements SpaceshipService {

	private @Autowired SpaceshipRepository spaceshipRepository;

	public Page<SpaceshipResponse> getAllSpaceships(Pageable pageable) {
		return spaceshipRepository.findAll(pageable).map(SpaceshipMapper.INSTANCE::fromDomain);
	}

	public SpaceshipResponse getSpaceshipById(Long id) {
		var model = spaceshipRepository.findById(id);

		return model.map(SpaceshipMapper.INSTANCE::fromDomain)
				.orElseThrow(() -> new ItemNotFoundException("Spaceship", id));
	}

	public List<SpaceshipResponse> getSpaceshipsByName(String name) {
		var models = spaceshipRepository.findBySpaceshipNameContaining(name);

		return models.parallelStream().map(SpaceshipMapper.INSTANCE::fromDomain).toList();
	}

	public SpaceshipResponse createSpaceship(SpaceshipCreateRequest spaceship) {
		var model = SpaceshipMapper.INSTANCE.fromCreateRequest(spaceship);

		return SpaceshipMapper.INSTANCE.fromDomain(spaceshipRepository.save(model));
	}

	public SpaceshipResponse updateSpaceship(Long id, SpaceshipUpdateRequest req) {
		var model = SpaceshipMapper.INSTANCE.fromUpdateRequest(getSpaceshipById(id).getId(), req);

		return SpaceshipMapper.INSTANCE.fromDomain(spaceshipRepository.save(model));
	}

	public void deleteSpaceship(Long id) {
		spaceshipRepository.deleteById(id);
	}
}
