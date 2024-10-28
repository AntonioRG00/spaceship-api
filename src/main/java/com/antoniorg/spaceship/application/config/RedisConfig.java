package com.antoniorg.spaceship.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import io.lettuce.core.ClientOptions;

@Configuration
@EnableCaching
public class RedisConfig {
	
    private @Value("${spring.data.redis.password}") String redisPwd;

	@Bean
	protected RedisStandaloneConfiguration redisStandaloneConfiguration() {
		var config = new RedisStandaloneConfiguration("redis-server", 6379);
		config.setPassword(redisPwd);
		
		return config;
	}

	@Bean
	protected ClientOptions clientOptions() {
		return ClientOptions.builder().disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS)
				.autoReconnect(true).build();
	}

	@Bean
	protected RedisConnectionFactory connectionFactory(RedisStandaloneConfiguration redisStandaloneConfiguration) {
		var configuration = LettuceClientConfiguration.builder().clientOptions(clientOptions()).build();

		return new LettuceConnectionFactory(redisStandaloneConfiguration, configuration);
	}

	@Bean
	@ConditionalOnMissingBean(name = "redisTemplate")
	@Primary
	protected RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		var template = new StringRedisTemplate();
		template.setConnectionFactory(redisConnectionFactory);
		
		return template;
	}
}
