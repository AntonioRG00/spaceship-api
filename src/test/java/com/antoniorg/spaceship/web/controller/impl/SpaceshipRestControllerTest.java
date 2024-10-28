package com.antoniorg.spaceship.web.controller.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;

import com.antoniorg.spaceship.application.dto.SpaceshipCreateRequest;
import com.antoniorg.spaceship.application.dto.SpaceshipResponse;
import com.antoniorg.spaceship.application.dto.SpaceshipUpdateRequest;
import com.antoniorg.spaceship.application.service.impl.SpaceshipServiceImpl;
import com.antoniorg.spaceship.infrastructure.kafka.SpaceshipProducer;

@ExtendWith(MockitoExtension.class)
class SpaceshipRestControllerTest {

	private @Mock SpaceshipServiceImpl spaceshipService;
	private @Mock SpaceshipProducer spaceshipProducer;
	private @InjectMocks SpaceshipRestController spaceshipRestController;

	@Test
	void testGetAllSpaceships() {
		var pageRequest = PageRequest.of(0, 5, Sort.by(Direction.ASC, "name"));
		var spaceshipPage = new PageImpl<>(List.of(new SpaceshipResponse()));
		when(spaceshipService.getAllSpaceships(pageRequest)).thenReturn(spaceshipPage);

		var response = spaceshipRestController.getAllSpaceships(0, 5, "name", Direction.ASC);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(spaceshipPage, response.getBody());
		verify(spaceshipService).getAllSpaceships(pageRequest);
	}

	@Test
	void testGetSpaceshipById() {
		var spaceshipResponse = new SpaceshipResponse();
		when(spaceshipService.getSpaceshipById(1L)).thenReturn(spaceshipResponse);

		var response = spaceshipRestController.getSpaceshipById(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(spaceshipResponse, response.getBody());
		verify(spaceshipService).getSpaceshipById(1L);
	}

	@Test
	void testGetSpaceshipsByName() {
		var spaceshipList = List.of(new SpaceshipResponse());
		when(spaceshipService.getSpaceshipsByName("Millennium Falcon")).thenReturn(spaceshipList);

		var response = spaceshipRestController.getSpaceshipsByName("Millennium Falcon");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(spaceshipList, response.getBody());
		verify(spaceshipService).getSpaceshipsByName("Millennium Falcon");
	}

	@Test
	void testCreateSpaceship() {
		var createRequest = new SpaceshipCreateRequest();
		var spaceshipResponse = new SpaceshipResponse();
		when(spaceshipService.createSpaceship(createRequest)).thenReturn(spaceshipResponse);

		var response = spaceshipRestController.createSpaceship(createRequest);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(spaceshipResponse, response.getBody());
		verify(spaceshipService).createSpaceship(createRequest);
		verify(spaceshipProducer).sendSpaceshipCreatedEvent(spaceshipResponse);
	}

	@Test
	void testUpdateSpaceship() {
		var updateRequest = new SpaceshipUpdateRequest();
		var spaceshipResponse = new SpaceshipResponse();
		when(spaceshipService.updateSpaceship(1L, updateRequest)).thenReturn(spaceshipResponse);

		var response = spaceshipRestController.updateSpaceship(1L, updateRequest);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(spaceshipResponse, response.getBody());
		verify(spaceshipService).updateSpaceship(1L, updateRequest);
	}

	@Test
	void testDeleteSpaceship() {
		doNothing().when(spaceshipService).deleteSpaceship(1L);

		var response = spaceshipRestController.deleteSpaceship(1L);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		verify(spaceshipService).deleteSpaceship(1L);
	}
}