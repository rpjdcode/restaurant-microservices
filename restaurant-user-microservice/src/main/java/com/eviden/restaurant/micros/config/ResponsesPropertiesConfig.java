package com.eviden.restaurant.micros.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.eviden.restaurant.micros.util.RegExpPatterns;

import lombok.Data;

@Data
@Configuration
@PropertySource("classpath:responses.properties")
@ComponentScan
public class ResponsesPropertiesConfig {

	/* MENSAJES DE ÉXITO Y ERROR DE LA API */
	@Value("${user.found}")
	private String userFoundMsg;
	
	@Value("${user.inserted}")
	private String userInsertedMsg;
	
	@Value("${user.updated}")
	private String userUpdatedMsg;
	
	@Value("${user.update.fail}")
	private String userUpdateFailMsg;
	
	@Value("${user.deleted}")
	private String userDeletedMsg;
	
	@Value("${user.err.notfound}")
	private String userNotFoundMsg;
	
	@Value("${user.err.validation}")
	private String userNotValidatedMsg;
	
	@Value("${user.err.username.existing}")
	private String userExistingMsg;
	
	@Value("${user.err.email.existing}")
	private String userEmailExistingMsg;
	
	@Value("${user.err.delete.notfound}")
	private String userDeleteNotFoundMsg;
	
	@Value("${user.validation.username.null}")
	private String usernameValidationNullMessage;
	
	@Value("${user.validation.username.empty}")
	private String usernameValidationEmptyMessage;
	
	@Value("${user.validation.username.format}")
	private String usernameValidationFormatMessage;
	
	@Value("${user.validation.email.null}")
	private String emailValidationNullMessage;
	
	@Value("${user.validation.email.empty}")
	private String emailValidationEmptyMessage;
	
	@Value("${user.validation.email.format}")
	private String emailValidationFormatMessage;
	
	@Value("${user.validation.update.data.null}")
	private String userUpdateDataNullMessage;
	
	@Value("${user.validation.update.id.null}")
	private String userUpdateIdentifierNullMessage;
	
	@Value("${user.validation.update.id.notfound}")
	private String userUpdateIdentifierNotFoundMessage;
	
	@Bean
	RegExpPatterns regExpPatterns() {
		return new RegExpPatterns();
	}
	

	
}
