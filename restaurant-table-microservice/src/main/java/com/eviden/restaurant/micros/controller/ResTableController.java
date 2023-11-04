package com.eviden.restaurant.micros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eviden.restaurant.micros.dto.ResTableDTO;
import com.eviden.restaurant.micros.entity.ResTable;
import com.eviden.restaurant.micros.service.ResTableService;

@RestController
@RequestMapping("/api/tables")
public class ResTableController {

	@Autowired
	private ResTableService service;
	
	@GetMapping
	public ResponseEntity<?> rootEndpoint() {
		List<ResTable> tables = service.findAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(tables);
	}
	
	@GetMapping("/table")
	public ResponseEntity<?> getTableById(@RequestParam("id") long id){
		ResTable table = service.findById(id);
		
		if (table != null) {
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResTableDTO(table));
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo encontrar la mesa indicada");
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> insertTableEndpoint(@RequestBody ResTableDTO data) {
		
		ResTable tableNew = service.saveAndFlush(new ResTable(data));
		
		return ResponseEntity.ok(tableNew);
	}
	
}
