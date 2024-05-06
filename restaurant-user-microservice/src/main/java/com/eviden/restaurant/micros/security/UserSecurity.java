package com.eviden.restaurant.micros.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;

import com.eviden.restaurant.micros.security.jwt.CustomJwt;
import com.eviden.restaurant.micros.security.jwt.CustomJwtConverter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class UserSecurity {
	
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    	 DelegatingJwtGrantedAuthoritiesConverter authoritiesConverter =
//    	          // Using the delegating converter multiple converters can be combined
//    	          new DelegatingJwtGrantedAuthoritiesConverter(
//    	                  // First add the default converter
//    	                  new JwtGrantedAuthoritiesConverter(),
//    	                  // Second add our custom Keycloak specific converter
//    	                  new KeycloakJwtRolesConverter());
        
        http.authorizeHttpRequests(customizer -> {
        	customizer.requestMatchers("/**").hasAuthority("ADMIN");
        	customizer.anyRequest().authenticated();
        }).oauth2ResourceServer(resourceServer -> resourceServer.jwt(jwt -> {
        	jwt.jwtAuthenticationConverter(customJwtConverter());
        }));
        
        return http.build();
    }
    
//    @Bean
//    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//        return new NullAuthenticatedSessionStrategy();
//    }
    
    @Bean
    Converter<Jwt, CustomJwt> customJwtConverter() {
    	return new CustomJwtConverter();
    }

}
