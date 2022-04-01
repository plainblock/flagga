package io.github.plainblock.flagga.jwt.controller;

import io.github.plainblock.flagga.jwt.controller.constant.Mapping;
import org.junit.jupiter.api.Test;

class HS256TokenControllerTest extends TokenControllerTestBase {

	private static final String CONTROLLER = "jwt/hs256";
	
	@Test
	void token() throws Exception {
		String endpoint = Mapping.HS256 + Mapping.TOKEN;
		executeGet(CONTROLLER, endpoint);
	}
	
	@Test
	void decode() throws Exception {
		String endpoint = Mapping.HS256 + Mapping.DECODE;
		final String token = hs256TokenGenerator.generate("hs256", "HS256 algorithm token");
		executeGet(CONTROLLER, endpoint, token);
	}
	
	@Test
	void verify() throws Exception {
		String endpoint = Mapping.HS256 + Mapping.VERIFY;
		final String token = hs256TokenGenerator.generate("hs256", "HS256 algorithm token");
		executeGet(CONTROLLER, endpoint, token);
	}
	
	@Test
	void flag() throws Exception {
		String endpoint = Mapping.HS256 + Mapping.FLAG;
		executeGet(CONTROLLER, endpoint, null, "Flag string");
	}

}
