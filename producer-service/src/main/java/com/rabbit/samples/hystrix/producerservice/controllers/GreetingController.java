package com.rabbit.samples.hystrix.producerservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 24 Mar 2019
 */
@Slf4j
@RestController
@RequestMapping("/greeting")
public class GreetingController {

	@GetMapping("/{username}")
	public String greeting(@PathVariable final String username) {

		log.info("greeting to {}", username);

		return String.format("Hello %s!\n", username);
	}

}
