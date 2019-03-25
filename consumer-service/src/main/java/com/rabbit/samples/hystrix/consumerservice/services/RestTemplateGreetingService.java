package com.rabbit.samples.hystrix.consumerservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 24 Mar 2019
 */
@Slf4j
@Getter(AccessLevel.PROTECTED)
@Service
public class RestTemplateGreetingService {

	@Value("${producer.service.url}")
	String producerUrl;

	@Value("${producer.service.path}")
	String producerPath;

	// PLEASE NOTE: Note that the fallback method should have the same signature of the wrapped method and must reside in the same class.
	@HystrixCommand(fallbackMethod = "defaultGreeting")
	// PLEASE NOTE: Normally a @HytrixCommand annotated method is executed in a thread pool context. But sometimes it needs to be running in a local scope,
	// for example, a @SessionScope or a @RequestScope. This can be done via giving arguments to the command annotation
	// @HystrixCommand(fallbackMethod = "defaultGreeting", commandProperties = {
	// 		@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")
	// })
	public String getGreeting(final String username) {

		log.debug("greeting to {}", username);

		return new RestTemplate().getForObject(getProducerEndpoint(), String.class, username);
	}

	private String getProducerEndpoint() {

		return getProducerUrl() + getProducerPath()+ "/greeting/{username}";
	}

	private String defaultGreeting(final String username) {

		log.warn("FALLBACK: greeting to ANONYMOUS instead of {}", username);

		return "Hello ANONYMOUS!";
	}

}
