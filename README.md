
# Spring Cloud Netflix Hystrix

## Sub-projects

* [Producer](producer-service)
* [Consumer](consumer-service)
* [Dashboard](dashboard)
* [Eureka server](eureka-server)

---

## Run

1. Start Redis
	```
	docker run -d --rm --name redis -p 6379:6379 redis:alpine redis-server --appendonly yes
	```

2. Start MySQL
	```
	docker run -d --rm --name mysql -e MYSQL_DATABASE=hystrix -e MYSQL_ROOT_PASSWORD=admin-secret -e MYSQL_USER=user -e MYSQL_PASSWORD=secret -p 3306:3306 -p 33060:33060 mysql
	```

3. Start Eureka server
	```
	cd ./eureka-server
	mvnw clean spring-boot:run
	```
	
4. Start producer service
	```
	cd ./producer-service
	mvnw clean spring-boot:run
	```

5. Start consumer service
	```
	cd ./consumer-service
	mvnw clean spring-boot:run
	```

6. Start dashboard
	```
	cd ./dashboard
	mvnw clean spring-boot:run
	```

---

## Links

### Producer
* [Greeting](http://localhost:8081/producer/greeting/matt)
* [User info](http://localhost:8081/producer/users/matt)
* [Actuator hystrix.stream](http://localhost:8091/producer/actuator/hystrix.stream)

### Consumer
* [Greeting via RestTemplate](http://localhost:8080/greeting/rest/matt)
* [Greeting via Feign](http://localhost:8080/greeting/feign/matt)
* [Actuator hystrix.stream](http://localhost:8090/actuator/hystrix.stream)

### Dashboard
* [Turbine](http://localhost:9080/turbine.stream?cluster=HYSTRIX-CLUSTER)
* [Turbine dashboard](http://localhost:9080/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A9080%2Fturbine.stream%3Fcluster%3DHYSTRIX-CLUSTER&delay=1000&title=hystrix-cluster)
* [Hystrix dashboard (just producer-service)](http://localhost:9080/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8091%2Factuator%2Fhystrix.stream&delay=1000&title=producer-service)
* [Hystrix dashboard (just consumer-service)](http://localhost:9080/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8090%2Factuator%2Fhystrix.stream&delay=1000&title=consumer-service)

---

## Links

### Hystrix
* https://www.baeldung.com/spring-cloud-netflix-hystrix
* https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html#spring-cloud-feign-hystrix
* http://nphumbert.github.io/blog/2017/07/23/setup-a-circuit-breaker-with-hystrix/

### Hystrix + Caching
* https://github.com/eugenp/tutorials/tree/master/spring-cloud/spring-cloud-bootstrap/svc-rating

### Turbine
* https://dzone.com/articles/spring-cloud-with-turbine
* https://cloud.spring.io/spring-cloud-consul/multi/multi_spring-cloud-consul-turbine.html

### Samples
* https://github.com/bijukunjummen/sample-spring-hystrix
* https://github.com/jpuigsegur/spring-cloud-netflix-sample

### Issues
* https://github.com/spring-cloud/spring-cloud-netflix/issues/948

### Spring Boot Admin (remooved froom version 2.x)
* https://codecentric.github.io/spring-boot-admin/1.5.1/#_ui_modules
