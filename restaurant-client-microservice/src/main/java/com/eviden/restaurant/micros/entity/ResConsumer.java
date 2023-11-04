package com.eviden.restaurant.micros.entity;

import com.eviden.restaurant.micros.dto.ResConsumerDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "consumers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResConsumer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "INT(11)", length = 11, nullable = false)
	private Long id;
	
	@Column(name = "full_name", columnDefinition = "VARCHAR(250)", length = 250, nullable = false)
	private String fullName;
	
	@Column(name = "identification", columnDefinition = "VARCHAR(9)", length = 9, nullable = false, unique = true)
	private String identification;
	
	@Column(name = "phone", columnDefinition = "VARCHAR(9)", length = 9, nullable = false, unique = true)
	private String phone;
	
	@Column(name = "email", columnDefinition = "VARCHAR(255)", length = 255, nullable = false, unique = true)
	private String email;
	
	public ResConsumer(ResConsumerDTO data) {
		this.fullName = data.getFullName();
		this.identification = data.getIdentification();
		this.phone = data.getPhone();
		this.email = data.getEmail();
	}
}
