package com.eviden.restaurant.micros.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import lombok.Data;

@Data
@Configuration
@PropertySource("classpath:rest_template.properties")
@ComponentScan
public class RestTemplateConfig {
	
	@Value("${template.bookings.baseurl}")
	private String bookingsBaseUrl;
	
	@Value("${template.bookings.port}")
	private String bookingsPort;
	
	@Value("${template.bookings.endpoint}")
	private String bookingsEndpoint;
	
	@Value("${eureka.bookings.baseurl}")
	private String eurekaBookingsBaseUrl;
	
	@Value("${eureka.bookings.micro}")
	private String eurekaBookingsMicroName;

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
