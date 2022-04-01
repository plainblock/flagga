package io.github.plainblock.flagga.jwt.controller;

import io.github.plainblock.flagga.jwt.controller.constant.Mapping;
import org.junit.jupiter.api.Test;

class NoneTokenControllerTest extends TokenControllerTestBase {

	private static final String CONTROLLER = "jwt/none";
	
	@Test
	void token() throws Exception {
		String endpoint = Mapping.NONE + Mapping.TOKEN;
		executeGet(CONTROLLER, endpoint);
	}
	
	@Test
	void decode() throws Exception {
		String endpoint = Mapping.NONE + Mapping.DECODE;
		final String token = noneTokenGenerator.generate("none", "None algorithm token");
		executeGet(CONTROLLER, endpoint, token);
	}
	
	@Test
	void verify() throws Exception {
		String endpoint = Mapping.NONE + Mapping.VERIFY;
		final String token = noneTokenGenerator.generate("none", "None algorithm token");
		executeGet(CONTROLLER, endpoint, token);
	}
	
	@Test
	void flag() throws Exception {
		String endpoint = Mapping.NONE + Mapping.FLAG;
		executeGet(CONTROLLER, endpoint, null, "Flag string");
	}

}
