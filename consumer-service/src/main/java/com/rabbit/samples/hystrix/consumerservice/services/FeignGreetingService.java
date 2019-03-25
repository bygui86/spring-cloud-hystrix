package com.rabbit.samples.hystrix.consumerservice.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 24 Mar 2019
 */
@FeignClient(
		name = "producer-service",
		url = "${producer.service.url}",
		path = "${producer.service.path}",
		// WARNING: we must enable Feign Hystrix method wrapping (see 'application.properties'), otherwise fallbacks won't work
		// fallback = FallbackFeignGreetingService.class
		fallbackFactory = FallbackFeignGreetingServiceFactory.class
)
public interface FeignGreetingService {

	@GetMapping("/greeting/{username}")
	String getGreeting(@PathVariable("username") final String username);

}
