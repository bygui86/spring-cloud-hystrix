package com.rabbit.samples.hystrix.producerservice.repos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rabbit.samples.hystrix.producerservice.domain.UserInfo;
import com.rabbit.samples.hystrix.producerservice.domain.UserInfoList;

import java.io.IOException;
import java.util.Optional;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 24 Mar 2019
 */
public interface CachedUserInfoRepo {

	void insertAll(final UserInfoList userInfoList) throws JsonProcessingException;

	void insert(final UserInfo userInfo) throws JsonProcessingException;

	UserInfoList findAll() throws IOException;

	Optional<UserInfo> findById(final Long id) throws IOException;

}
