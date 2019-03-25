package com.rabbit.samples.hystrix.consumerservice.configs;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Configuration;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 24 Mar 2019
 */
@Configuration
@EnableCircuitBreaker
public class CircuitBreakConfig {

	// no-op
}
