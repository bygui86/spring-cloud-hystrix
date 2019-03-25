package com.rabbit.samples.hystrix.producerservice.controllers;

import com.rabbit.samples.hystrix.producerservice.domain.UserInfo;
import com.rabbit.samples.hystrix.producerservice.domain.UserInfoList;
import com.rabbit.samples.hystrix.producerservice.services.UserInfoService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 24 Mar 2019
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
@RestController
@RequestMapping("/users")
public class UserInfoController {

	UserInfoService userInfoService;

	@GetMapping
	public UserInfoList getAll() throws IOException {

		log.info("Get all UserInfo");

		return getUserInfoService().getAllUserInfo();
	}

	@GetMapping("/{id}")
	public Optional<UserInfo> getById(@PathVariable final Long id) throws IOException {

		log.info("Get UserInfo by id {}", id);

		return getUserInfoService().getUserInfoById(id);
	}

}
