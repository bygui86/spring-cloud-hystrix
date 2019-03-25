package com.rabbit.samples.hystrix.consumerservice.services;

import feign.FeignException;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 24 Mar 2019
 */
@Slf4j
@Component
public class FallbackFeignGreetingServiceFactory implements FallbackFactory<FeignGreetingService> {

	@Override
	public FeignGreetingService create(Throwable cause) {

		if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
			log.error("A FeignException occurred: {}", cause.getMessage());
		}

		return username -> {
			log.warn("FALLBACK: greeting to ANONYMOUS instead of {}", username);
			return "Hello ANONYMOUS!";
		};
	}

}
