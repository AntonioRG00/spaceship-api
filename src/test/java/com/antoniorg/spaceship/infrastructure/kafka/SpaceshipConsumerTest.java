package com.antoniorg.spaceship.infrastructure.kafka;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import com.antoniorg.spaceship.application.dto.SpaceshipResponse;

@ExtendWith(MockitoExtension.class)
class SpaceshipConsumerTest {

	private @Mock Logger log;
	private @InjectMocks @Spy SpaceshipConsumer spaceshipConsumer;

	@Test
	void testConsume() {
		var spaceshipResponse = new SpaceshipResponse();
		spaceshipResponse.setId(1L);
		spaceshipResponse.setSpaceshipName("Millennium Falcon");
		spaceshipResponse.setMovieName("Star Wars");
		spaceshipResponse.setMovieYear(1977);

		spaceshipConsumer.consume(spaceshipResponse);

		verify(spaceshipConsumer).consume(spaceshipResponse);
	}
}
