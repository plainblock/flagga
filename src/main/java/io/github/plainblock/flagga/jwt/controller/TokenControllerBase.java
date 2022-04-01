package io.github.plainblock.flagga.jwt.controller;

import org.slf4j.Logger;

import io.github.plainblock.flagga.jwt.controller.constant.Header;

public class TokenControllerBase {

	static final String RN = "\r\n";

	// Response
	static final String TOKEN_REQUIRED_ERROR = "Token is required: Please set header \"" + Header.TOKEN + "\"." + RN;
	static final String FLAG_REQUIRED_ERROR = "Flag is required: Please set header \"" + Header.FLAG + "\"." + RN;
	static final String FLAG_WRONG_RESPONSE = "Flag is wrong!" + RN;
	static final String FLAG_CORRECT_RESPONSE = "Flag is correct! Congratulations!" + RN;

	static void loggingHeader(Logger logger, String token, String flag) {
		if (token != null && !token.isBlank()) logger.debug(Header.TOKEN + ": " + token);
		if (flag != null && !flag.isBlank()) logger.debug(Header.FLAG + ": " + flag);
	}
	
}
