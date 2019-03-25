package com.rabbit.samples.hystrix.consumerservice.configs;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 24 Mar 2019
 */
@Configuration
@EnableFeignClients(basePackages = "com.rabbit.samples.hystrix.consumerservice.services")
public class FeignConfig {

	// no-op
}
