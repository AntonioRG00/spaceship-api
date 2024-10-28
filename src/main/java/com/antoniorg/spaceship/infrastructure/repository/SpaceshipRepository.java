package com.antoniorg.spaceship.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antoniorg.spaceship.domain.model.SpaceshipModel;

import java.util.List;

@Repository
public interface SpaceshipRepository extends JpaRepository<SpaceshipModel, Long> {
	List<SpaceshipModel> findBySpaceshipNameContaining(String name);
}