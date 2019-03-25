package com.rabbit.samples.hystrix.consumerservice.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 24 Mar 2019
 */
@Slf4j
// @Component
public class FallbackFeignGreetingService implements FeignGreetingService {

	// PLEASE NOTE: Note that the fallback method should have the same signature of the wrapped method
	@Override
	public String getGreeting(final String username) {

		log.warn("FALLBACK: greeting to ANONYMOUS instead of {}", username);

		return "Hello ANONYMOUS!";
	}

}
