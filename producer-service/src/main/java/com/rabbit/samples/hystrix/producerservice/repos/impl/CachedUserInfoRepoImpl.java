package com.rabbit.samples.hystrix.producerservice.repos.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbit.samples.hystrix.producerservice.domain.UserInfo;
import com.rabbit.samples.hystrix.producerservice.domain.UserInfoList;
import com.rabbit.samples.hystrix.producerservice.repos.CachedUserInfoRepo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 24 Mar 2019
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter(AccessLevel.PROTECTED)
@Repository
public class CachedUserInfoRepoImpl implements CachedUserInfoRepo {

	static final String CACHE_ALL_KEY = "all";

	RedisTemplate<String, String> redisTemplate;

	ValueOperations<String, String> opsForValue;

	ObjectMapper jsonMapper;

	public CachedUserInfoRepoImpl(final RedisTemplate<String, String> redisTemplate) {

		this.redisTemplate = redisTemplate;

		jsonMapper = new ObjectMapper();
		opsForValue = getRedisTemplate().opsForValue();
	}

	@Override
	public void insertAll(final UserInfoList userInfoList) throws JsonProcessingException {

		log.info("Insert all in Redis: {}", userInfoList);

		userInfoList.setFromCache(true);
		userInfoList.setLastCacheUpdated(new Date());
		userInfoList.getUsers().forEach(userInfo -> userInfo.setFromCache(true));

		getOpsForValue().set(CACHE_ALL_KEY, getJsonMapper().writeValueAsString(userInfoList));
	}

	@Override
	public void insert(final UserInfo userInfo) throws JsonProcessingException {

		log.info("Insert in Redis: {}", userInfo);

		userInfo.setFromCache(true);
		userInfo.setLastCacheUpdated(new Date());
		getOpsForValue().set(userInfo.getId().toString(), getJsonMapper().writeValueAsString(userInfo));
	}

	@Override
	public UserInfoList findAll() throws IOException {

		log.info("Get all from Redis");

		final String cachedObj = getOpsForValue().get(CACHE_ALL_KEY);

		if(cachedObj != null) {
			log.info("UserInfoList found in Redis");
			return getJsonMapper().readValue(cachedObj, UserInfoList.class);
		}

		return UserInfoList.builder().fromCache(true).build();
	}

	@Override
	public Optional<UserInfo> findById(final Long id) throws IOException {

		log.info("Get from Redis by id: {}", id);

		final String cachedObj = getOpsForValue().get(id.toString());

		if(cachedObj != null) {
			log.info("UserInfo with id {} found in Redis", id);
			return Optional.of(getJsonMapper().readValue(cachedObj, UserInfo.class));
		}

		return Optional.empty();
	}

}
