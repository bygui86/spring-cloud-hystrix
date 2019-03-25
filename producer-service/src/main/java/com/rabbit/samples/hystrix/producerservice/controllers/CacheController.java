package com.rabbit.samples.hystrix.producerservice.controllers;

import com.rabbit.samples.hystrix.producerservice.services.CacheService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 06 Feb 2019
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter(value = AccessLevel.PROTECTED)
@RestController
@RequestMapping("/cache")
public class CacheController {

	CacheService cacheService;

	@GetMapping
	public Set<String> getAll() {

		log.info("Get all cache");

		return getCacheService().getAll();
	}

	@GetMapping("/{key}")
	public Object getByKey(@PathVariable final String key) {

		log.info("Get cache by key {}", key);

		return getCacheService().getByKey(key);
	}

	@DeleteMapping
	public void evictAll() {

		log.info("Delete all cache");

		getCacheService().evictAll();
	}

	@DeleteMapping("/{key}")
	public void evictByKey(@PathVariable final String key) {

		log.info("Delete cache by key {}", key);

		getCacheService().evictByKey(key);
	}

}
