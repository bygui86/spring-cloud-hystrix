package com.rabbit.samples.hystrix.producerservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 24 Mar 2019
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfoList implements Serializable {

	List<UserInfo> users;

	boolean fromCache;

	Date lastCacheUpdated;

	public UserInfoList(final List<UserInfo> users) {

		this.users = users;
	}

	@JsonIgnore
	public boolean isEmpty(){

		return getUsers() == null || getUsers().isEmpty();
	}

}
