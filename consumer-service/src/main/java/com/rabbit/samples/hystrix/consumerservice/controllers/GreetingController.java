package com.rabbit.samples.hystrix.consumerservice.controllers;

import com.rabbit.samples.hystrix.consumerservice.services.FeignGreetingService;
import com.rabbit.samples.hystrix.consumerservice.services.RestTemplateGreetingService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
@Controller
@RequestMapping("/greeting")
public class GreetingController {

	RestTemplateGreetingService restTemplateGreetingService;

	FeignGreetingService feignGreetingService;

	@GetMapping("/rest/{username}")
	public String getGreetingUsingRestTemplate(Model model, @PathVariable String username) {

		log.info("greeting to {}", username);

		model.addAttribute("greeting", getRestTemplateGreetingService().getGreeting(username));
		return "greeting-view";
	}

	@GetMapping("/feign/{username}")
	public String getGreetingUsingFeign(Model model, @PathVariable String username) {

		log.info("greeting to {}", username);

		model.addAttribute("greeting", getFeignGreetingService().getGreeting(username));
		return "greeting-view";
	}

}
