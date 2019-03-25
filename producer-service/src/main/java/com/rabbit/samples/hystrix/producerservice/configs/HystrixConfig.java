package com.rabbit.samples.hystrix.producerservice.configs;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;


/**
 * As the Hystrix capabilities are transparently injected as AOP advice, we have to adjust the order in which the advice is stacked,
 * in case if we have other advice like Spring’s transactional advice. Here we have adjusted the Spring’s transaction AOP advice to
 * have lower precedence than Hystrix AOP advice
 *
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 24 Mar 2019
 */
@Configuration
// @EnableHystrix
// @EnableTransactionManagement(
// 		order = Ordered.LOWEST_PRECEDENCE,
// 		mode = AdviceMode.ASPECTJ)
public class HystrixConfig {

	// @Bean
	// @Primary
	// @Order(Ordered.HIGHEST_PRECEDENCE)
	// public HystrixCommandAspect hystrixAspect() {
	//
	// 	return new HystrixCommandAspect();
	// }

}
