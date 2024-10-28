package com.antoniorg.spaceship.application.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.antoniorg.spaceship.application.dto.SpaceshipCreateRequest;
import com.antoniorg.spaceship.application.dto.SpaceshipResponse;
import com.antoniorg.spaceship.application.dto.SpaceshipUpdateRequest;
import com.antoniorg.spaceship.application.exception.ItemNotFoundException;
import com.antoniorg.spaceship.domain.model.SpaceshipModel;
import com.antoniorg.spaceship.infrastructure.repository.SpaceshipRepository;

@ExtendWith(MockitoExtension.class)
class SpaceshipServiceImplTest {

    private @Mock SpaceshipRepository spaceshipRepository;
    private @InjectMocks SpaceshipServiceImpl spaceshipService;

    @Test
    void testGetAllSpaceships() {
        var pageable = PageRequest.of(0, 5);
        var spaceship = new SpaceshipModel();
        
        var page = new PageImpl<>(List.of(spaceship));
        when(spaceshipRepository.findAll(pageable)).thenReturn(page);

        var result = spaceshipService.getAllSpaceships(pageable);

        assertEquals(1, result.getTotalElements());
        verify(spaceshipRepository).findAll(pageable);
    }

    @Test
    void testGetSpaceshipById() {
        var spaceship = new SpaceshipModel();
        spaceship.setId(1L);
        
        when(spaceshipRepository.findById(1L)).thenReturn(Optional.of(spaceship));

        var result = spaceshipService.getSpaceshipById(1L);

        assertEquals(spaceship.getId(), result.getId());
        verify(spaceshipRepository).findById(1L);
    }

    @Test
    void testGetSpaceshipById_NotFound() {
        when(spaceshipRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> spaceshipService.getSpaceshipById(1L));
    }

    @Test
    void testGetSpaceshipsByName() {
        var spaceship = new SpaceshipModel();
        spaceship.setSpaceshipName("Enterprise");

        when(spaceshipRepository.findBySpaceshipNameContaining("Enterprise")).thenReturn(List.of(spaceship));

        var result = spaceshipService.getSpaceshipsByName("Enterprise");

        assertEquals(1, result.size());
        verify(spaceshipRepository).findBySpaceshipNameContaining("Enterprise");
    }

    @Test
    void testCreateSpaceship() {
        var createRequest = new SpaceshipCreateRequest();
        var spaceship = new SpaceshipModel();
        var spaceshipResponse = new SpaceshipResponse();

        when(spaceshipRepository.save(any(SpaceshipModel.class))).thenReturn(spaceship);

        var result = spaceshipService.createSpaceship(createRequest);

        assertEquals(spaceshipResponse.getId(), result.getId());
        verify(spaceshipRepository).save(any(SpaceshipModel.class));
    }

    @Test
    void testUpdateSpaceship() {
        var updateRequest = new SpaceshipUpdateRequest();
        var spaceship = new SpaceshipModel();
        spaceship.setId(1L);

        when(spaceshipRepository.findById(1L)).thenReturn(Optional.of(spaceship));
        when(spaceshipRepository.save(any(SpaceshipModel.class))).thenReturn(spaceship);

        var result = spaceshipService.updateSpaceship(1L, updateRequest);

        assertEquals(spaceship.getId(), result.getId());
        verify(spaceshipRepository).save(any(SpaceshipModel.class));
    }

    @Test
    void testDeleteSpaceship() {
        doNothing().when(spaceshipRepository).deleteById(1L);

        spaceshipService.deleteSpaceship(1L);

        verify(spaceshipRepository).deleteById(1L);
    }
}