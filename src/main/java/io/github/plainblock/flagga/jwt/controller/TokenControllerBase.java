package io.github.plainblock.flagga.jwt.controller;

import org.slf4j.Logger;

import io.github.plainblock.flagga.jwt.controller.constant.Header;

public class TokenControllerBase {

	// Response
	static final String TOKEN_REQUIRED_ERROR = "Token is required: Please set header \"" + Header.TOKEN + "\".";
	static final String FLAG_REQUIRED_ERROR = "Flag is required: Please set header \"" + Header.FLAG + "\".";

	static final String FLAG_WRONG_RESPONSE = "Flag is wrong!";
	static final String FLAG_CORRECT_RESPONSE = "Flag is correct! Congratulations!";

	static void loggingHeader(Logger logger, String token, String flag) {
		if (token != null && !token.isBlank()) logger.debug(Header.TOKEN + ": " + token);
		if (flag != null && !flag.isBlank()) logger.debug(Header.FLAG + ": " + flag);
	}
	
}
