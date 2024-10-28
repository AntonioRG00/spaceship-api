package com.antoniorg.spaceship.infrastructure.kafka;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;

import com.antoniorg.spaceship.application.dto.SpaceshipResponse;

@ExtendWith(MockitoExtension.class)
class SpaceshipProducerTest {

    private @Mock KafkaTemplate<String, SpaceshipResponse> kafkaTemplate;
    private @Mock Logger log;
    private @InjectMocks SpaceshipProducer spaceshipProducer;

    @Test
    void testSendSpaceshipCreatedEvent() {
		var spaceshipResponse = new SpaceshipResponse();
		spaceshipResponse.setId(1L);
		spaceshipResponse.setSpaceshipName("Millennium Falcon");
		spaceshipResponse.setMovieName("Star Wars");
		spaceshipResponse.setMovieYear(1977);

        spaceshipProducer.sendSpaceshipCreatedEvent(spaceshipResponse);

        verify(kafkaTemplate).send("spaceship-topic", spaceshipResponse);
    }
}