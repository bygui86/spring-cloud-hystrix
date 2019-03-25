package com.rabbit.samples.hystrix.producerservice.repos;

import com.rabbit.samples.hystrix.producerservice.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 24 Mar 2019
 */
@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {

	// no-op
}
