package com.rabbit.samples.hystrix.producerservice.services;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 06 Feb 2019
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
@Service
public class CacheService {

	static final String WILDCARD = "*";

	CacheManager cacheManager;

	RedisTemplate<String, Object> redisTemplate;

	public Set<String> getAll() {

		return getRedisTemplate().keys(WILDCARD);
	}

	public Object getByKey(final String key) {

		return getRedisTemplate().keys(key + WILDCARD);
	}

	public void evictAll() {

		getCacheManager()
				.getCacheNames()
				.forEach(
						cacheName -> getCacheManager().getCache(cacheName).clear()
				);
	}

	public void evictByKey(final String key) {

		getCacheManager()
				.getCache(key)
				.clear();
	}

}
