package com.eviden.restaurant.micros.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

@Configuration
@EnableWebFluxSecurity
public class ResSecurityConfig {

	
//	@Bean
//	WebSecurityCustomizer webSecurityCustomizer() {
//		return (web) -> web.ignoring().requestMatchers("/ignorar");
//	}

	
	@Bean
	SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
	      http
          // ...
          .authorizeExchange((exchanges) ->
              exchanges
                  // any URL that starts with /admin/ requires the role "ROLE_ADMIN"
//                  .pathMatchers("/admin/**").hasRole("ADMIN")
//                  // a POST to /users requires the role "USER_POST"
//                  .pathMatchers(HttpMethod.POST, "/users").hasAuthority("Usuario")
//                  // a request to /users/{username} requires the current authentication's username
//                  // to be equal to the {username}
//                  .pathMatchers("/users/{username}").access((authentication, context) ->
//                      authentication
//                          .map(Authentication::getName)
//                          .map((username) -> username.equals(context.getVariables().get("username")))
//                          .map(AuthorizationDecision::new)
//                  )
                  // allows providing a custom matching strategy that requires the role "ROLE_CUSTOM"
//                  .matchers(customMatcher).hasRole("CUSTOM")
                  // any other request requires the user to be authenticated
                  .anyExchange().authenticated().and().oauth2Login(Customizer.withDefaults())
          );
	      
//	      http.oauth2Login(Customizer.withDefaults());
	      http.csrf(csrf -> csrf.disable());
      return http.build();
	}
	
	
//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		
// 		http
//			.authorizeHttpRequests((authorizeHttpRequests) -> {
////				authorizeHttpRequests
////				.anyRequest().authenticated();
//				authorizeHttpRequests.anyRequest().hasAnyRole("Usuario");
//			})
//			.oauth2ResourceServer(resourceServer -> resourceServer.jwt(jwt -> jwt.decoder(jwtDecoder())))
//			.formLogin(Customizer.withDefaults());
//		
//		return http.build();
//		
//	}

}
