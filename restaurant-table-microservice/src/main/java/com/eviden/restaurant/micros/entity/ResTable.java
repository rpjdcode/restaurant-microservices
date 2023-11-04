package com.eviden.restaurant.micros.entity;

import com.eviden.restaurant.micros.dto.ResTableDTO;

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
@Table(name = "restaurant_tables")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "INT(11)", length = 11, nullable = false)
	private Long id;
	
	@Column(name = "number_identifier", columnDefinition = "INT(3)", length = 3, nullable = true)
	private Integer numberIdentifier;
	
	@Column(name = "capacity", columnDefinition = "INT(3)", length = 3, nullable = false)
	private Integer capacity;
	
	@Column(name = "available", columnDefinition = "TINYINT(1)", length = 1, nullable = false)
	private Boolean available;
	
	public ResTable(ResTableDTO dto) {
		this.numberIdentifier = dto.getNumberIdentifier();
		this.capacity = dto.getCapacity();
		this.available = dto.getAvailable();
	}
	
	
}
