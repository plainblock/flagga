package io.github.plainblock.flagga.jwt.service;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TokenServiceBase {
	
	static final ObjectMapper MAPPER = new ObjectMapper();
	static final String DEFAULT_SUBJECT = "user";
	static final String DEFAULT_MESSAGE = "This is test token.";

}
