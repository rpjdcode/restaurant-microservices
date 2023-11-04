package com.eviden.restaurant.micros.dto;

import com.eviden.restaurant.micros.entity.ResConsumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResConsumerDTO {

	private String fullName;
	private String identification;
	private String phone;
	private String email;
	
	public ResConsumerDTO(ResConsumer consumer) {
		this.fullName = consumer.getFullName();
		this.identification = consumer.getIdentification();
		this.phone = consumer.getPhone();
		this.email = consumer.getEmail();
	}
}
