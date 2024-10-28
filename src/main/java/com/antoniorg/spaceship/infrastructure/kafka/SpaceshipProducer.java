package com.antoniorg.spaceship.infrastructure.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.antoniorg.spaceship.application.dto.SpaceshipResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SpaceshipProducer {

    private @Autowired KafkaTemplate<String, SpaceshipResponse> kafkaTemplate;

    public void sendSpaceshipCreatedEvent(SpaceshipResponse spaceship) {
        kafkaTemplate.send("spaceship-topic", spaceship);
        
        log.info("Message sended to spaceship-topic: " + spaceship);
    }
}