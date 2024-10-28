package com.antoniorg.spaceship.application.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

import io.lettuce.core.ClientOptions;

@SpringBootTest
@ActiveProfiles("test")
class RedisConfigTest {

	private @Autowired ApplicationContext applicationContext;

	@Test
	void testRedisStandaloneConfigurationBean() {
		var redisStandaloneConfig = applicationContext.getBean(RedisStandaloneConfiguration.class);
		assertNotNull(redisStandaloneConfig, "RedisStandaloneConfiguration bean should not be null");
		assertNotNull(redisStandaloneConfig.getPassword(), "Redis password should not be null");
	}

	@Test
	void testClientOptionsBean() {
		var clientOptions = applicationContext.getBean("clientOptions", ClientOptions.class);
		assertNotNull(clientOptions, "ClientOptions bean should not be null");
	}

	@Test
	void testRedisConnectionFactoryBean() {
		var connectionFactory = applicationContext.getBean("connectionFactory", RedisConnectionFactory.class);
		assertNotNull(connectionFactory, "RedisConnectionFactory bean should not be null");
	}

	@Test
	void testRedisTemplateBean() {
		var redisTemplate = applicationContext.getBean("redisTemplate", RedisTemplate.class);
		assertNotNull(redisTemplate, "RedisTemplate bean should not be null");
		assertNotNull(redisTemplate.getConnectionFactory(), "RedisTemplate's ConnectionFactory should not be null");
	}
}
