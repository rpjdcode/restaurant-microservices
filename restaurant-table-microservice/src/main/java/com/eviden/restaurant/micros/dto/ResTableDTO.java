package com.eviden.restaurant.micros.dto;

import com.eviden.restaurant.micros.entity.ResTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResTableDTO {

	private Integer capacity;
	private Integer numberIdentifier;
	private Boolean available;
	
	public ResTableDTO(ResTable table) {
		this.capacity = table.getCapacity();
		this.numberIdentifier = table.getNumberIdentifier();
		this.available = table.getAvailable();
	}
}
