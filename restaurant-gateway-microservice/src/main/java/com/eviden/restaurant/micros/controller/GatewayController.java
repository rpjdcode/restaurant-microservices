package com.eviden.restaurant.micros.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class GatewayController {

	@GetMapping
	public ResponseEntity<?> rootEndpoint() {
		System.err.println("ROOT ENDPOINT");
		return ResponseEntity.ok("ROOT ENDPOINT");
	}
	
	@GetMapping("/auth")
	public ResponseEntity<?> obtenerAutenticacion(Authentication autenticacion) {
		return ResponseEntity.ok(autenticacion);
	}

	@GetMapping("/token")
	public ResponseEntity<?> getToken(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client) {
		return ResponseEntity.ok(client.getAccessToken().getTokenValue());
	}
}
