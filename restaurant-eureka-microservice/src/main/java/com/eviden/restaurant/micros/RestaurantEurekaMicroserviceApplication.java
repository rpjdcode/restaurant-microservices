package com.eviden.restaurant.micros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RestaurantEurekaMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantEurekaMicroserviceApplication.class, args);
	}

}
