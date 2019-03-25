package com.rabbit.samples.hystrix.producerservice.configs;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 06 Feb 2019
 */
@Configuration
@EnableCaching
public class CacheConfig {

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {

		return new JedisConnectionFactory();
	}

	@Bean
	RedisTemplate<String, Object> redisTemplate() {

		final RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}

}
