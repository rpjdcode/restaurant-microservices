package com.eviden.restaurant.micros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

@SpringBootApplication
//@EnableWebFluxSecurity
public class RestaurantGatewayMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantGatewayMicroserviceApplication.class, args);
	}

}
