package io.github.plainblock.flagga.jwt.domain.verifier;

import io.github.plainblock.flagga.jwt.domain.VerificationInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.plainblock.flagga.jwt.domain.TokenInfo;

class NoneTokenVerifierTest extends TokenVerifierTestBase {
	
	private static final String TARGET_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJub25lIn0.eyJzdWIiOiJhZG1pbiIsImlzcyI6InBsYWluYmxvY2siLCJhZG0iOmZhbHNlLCJleHAiOjE2NDg1NDQ5MzcsImlhdCI6MTY0ODU0MTMzN30.";

	@BeforeAll
	static void before() {
		verifier = new NoneTokenVerifier();
		mapper = new ObjectMapper();
	}

	@Test
	void decodeTest() {
		DecodedJWT jwt = verifier.decode(TARGET_TOKEN);
		String json = new TokenInfo(jwt).toJson(mapper);
		System.out.println("None decode: " + json);
	}
	
	@Test
	void verifyTest() {
		VerificationInfo info = verifier.verify(TARGET_TOKEN);
		System.out.println("HS256 verify: " + (info.jwt() != null ? "OK" : "NG"));
	}

}
