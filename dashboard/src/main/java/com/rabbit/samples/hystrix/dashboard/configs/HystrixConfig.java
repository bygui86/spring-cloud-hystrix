package com.rabbit.samples.hystrix.dashboard.configs;

import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Configuration;


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
@EnableHystrixDashboard
public class HystrixConfig {

	// no-op
}
