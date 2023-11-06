package com.eviden.restaurant.micros.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "beverages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Beverage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "INT(11)", length = 11, nullable = false)
	private Long id;
	
	@Column(name = "name", columnDefinition = "VARCHAR(100)", length = 100, nullable = false)
	private String name;
	
	@Enumerated(EnumType.STRING)
	private BeverageType type;
	
	@Column(name = "price", columnDefinition = "DECIMAL(6,2)", length = 6, precision = 2, nullable = false )
	private BigDecimal price;
	
	@Column(name = "photo", columnDefinition = "VARCHAR(255)", length = 255, nullable = true)
	private String photo;
}
