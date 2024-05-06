package com.eviden.restaurant.micros.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class GatewaySecurityConfig {
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setExposedHeaders(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
		http.cors(customizer -> {
			customizer.configurationSource(corsConfigurationSource());
		});
		
		http.csrf(csrf -> csrf.disable());
		
		http.headers(h -> h.frameOptions(fo -> fo.disable()));
		http.authorizeExchange(customizer -> {
			// Aquí se definirán las URLS de la aplicación que serán accesibles según los roles
			/*
			 * Funciona mejor este enfoque ya que la seguridad se implementa a nivel de URL
			 * Además, el token jwt original debe ser procesado por el converter
			 * personalizado, que se encarga de extraer los roles del realm
			 * y los prepara como GrantedAuthorities del token.
			 * 
			 * Por ello, puede ser que implementar métodos @PreAuthorize
			 * no funcionen correctamente, incluso implementando
			 * @EnableMethodSecurity
			 */
			customizer.pathMatchers("/auth").hasAuthority("ADMIN");
			customizer.pathMatchers("/").hasAuthority("USER");
			customizer.pathMatchers("/api/users").hasAuthority("ADMIN");
			customizer.anyExchange().authenticated();
			
		}).oauth2ResourceServer(resourceServer -> {
			resourceServer.jwt(jwt -> {
				jwt.jwtAuthenticationConverter(customJwtConverter());
			});
		});

		return http.build();
	}
	
	@Bean
	Converter<Jwt, Mono<AbstractAuthenticationToken>> customJwtConverter() {
		return new CustomJwtConverterMono();
	}

}
