package com.antoniorg.spaceship.infrastructure.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.antoniorg.spaceship.application.dto.SpaceshipResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SpaceshipConsumer {
    @KafkaListener(topics = "spaceship-topic", groupId = "spaceship-group")
    public void consume(SpaceshipResponse spaceship) {
        log.info("Message received spaceship-topic: " + spaceship);
    }
}
