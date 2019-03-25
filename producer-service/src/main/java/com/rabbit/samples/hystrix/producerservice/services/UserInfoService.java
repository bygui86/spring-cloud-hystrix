package com.rabbit.samples.hystrix.producerservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rabbit.samples.hystrix.producerservice.domain.UserInfo;
import com.rabbit.samples.hystrix.producerservice.domain.UserInfoList;
import com.rabbit.samples.hystrix.producerservice.repos.CachedUserInfoRepo;
import com.rabbit.samples.hystrix.producerservice.repos.UserInfoRepo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
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
@Service
public class UserInfoService {

	UserInfoRepo userInfoRepo;

	CachedUserInfoRepo cachedUserInfoRepo;

	// PLEASE NOTE: Note that the fallback method should have the same signature of the wrapped method and must reside in the same class.
	@HystrixCommand(
			commandKey = "allUserInfoFromDB",
			fallbackMethod = "getCachedAllUserInfo"
	)
	public UserInfoList getAllUserInfo() throws IOException {

		log.info("Get all UserInfo");

		List<UserInfo> userInfos = getUserInfoRepo().findAll();
		UserInfoList userInfoList = UserInfoList.builder().users(userInfos).build();
		if (getCachedAllUserInfo().isEmpty()
			&& ! userInfoList.isEmpty()){

			log.info("UserInfoList not in Redis, insert it...");

			getCachedUserInfoRepo().insertAll(userInfoList);
		}
		return userInfoList;
	}

	protected UserInfoList getCachedAllUserInfo() throws IOException {

		log.info("Retrieving all UserInfo from Redis");

		return getCachedUserInfoRepo().findAll();
	}

	// PLEASE NOTE: Note that the fallback method should have the same signature of the wrapped method and must reside in the same class.
	@HystrixCommand(
			commandKey = "userInfoByIdFromDB",
			fallbackMethod = "getCachedUserInfo"
	)
	public Optional<UserInfo> getUserInfoById(final Long id) throws IOException {

		log.info("Get UserInfo by id {}", id);

		Optional<UserInfo> userInfo = getUserInfoRepo().findById(id);
		if (getCachedUserInfo(id).isEmpty()
			&& userInfo.isPresent()) {

			log.info("UserInfo not in Redis, insert it...");

			getCachedUserInfoRepo().insert(userInfo.get());
		}
		return userInfo;
	}

	protected Optional<UserInfo> getCachedUserInfo(final Long id) throws IOException {

		log.info("Retrieving UserInfo by id {} from Redis", id);

		return getCachedUserInfoRepo().findById(id);
	}

}
