package com.eviden.restaurant.micros.security.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class CustomJwtConverter implements Converter<Jwt, CustomJwt> {

	@Override
	public CustomJwt convert(Jwt source) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		CustomJwt customJwt = new CustomJwt(source, grantedAuthorities);
		customJwt.setFirstName(source.getClaimAsString("first_name"));
		customJwt.setUsername(source.getClaimAsString("given_name"));
		customJwt.setLastName(source.getClaimAsString("family_name"));

		return customJwt;
	}

}
