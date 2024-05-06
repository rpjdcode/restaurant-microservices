//package com.eviden.restaurant.micros.security;
//
//import java.util.Collection;
//import java.util.Map;
//import java.util.Set;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomJwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {
//
//
//	private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//	@Override
//	public AbstractAuthenticationToken convert(Jwt source) {
//		Collection<GrantedAuthority> authorities = Stream.concat(
//				jwtGrantedAuthoritiesConverter.convert(source).stream(),
//				extraerRolesRealm(source).stream()
//				).collect(Collectors.toSet());
//		
//		
//		return new JwtAuthenticationToken(source, authorities, source.getClaim("preferred_username"));
//	}
//	
//	private Collection<GrantedAuthority> extraerRolesRealm(Jwt jwt) {
//		Map<String, Object> realmAccess;
//		Collection<String> roles;
//		
//		if (jwt.getClaim("realm_access") == null) {
//			return Set.of();
//		}
//		
//		realmAccess = jwt.getClaim("realm_access");
//		roles = (Collection<String>) realmAccess.get("roles");
//		
//		return roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toSet());
//	}
//	
//	private Collection<GrantedAuthority> extraerRolesRecurso(Jwt jwt) {
//		Map<String, Object> resourceAccess;
//		Map<String, Object> clientSources;
//		Collection<String> roles;
//		
//		if (jwt.getClaim("resource_access") == null) {
//			return Set.of();
//		}
//		
//		resourceAccess = jwt.getClaim("resource_access");
//		clientSources =  (Map<String, Object>)resourceAccess.get("rest-client");
//		
//		if (clientSources == null) {
//			return Set.of();
//		}
//		
//		roles = (Collection<String>)clientSources.get("roles");
//		
//		
//		return roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toSet());
//	}
//}
