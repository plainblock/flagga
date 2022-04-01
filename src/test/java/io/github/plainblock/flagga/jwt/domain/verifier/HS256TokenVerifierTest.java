package io.github.plainblock.flagga.jwt.domain.verifier;

import io.github.plainblock.flagga.jwt.domain.TokenInfo;
import io.github.plainblock.flagga.jwt.domain.VerificationInfo;
import io.github.plainblock.flagga.jwt.domain.constant.TestConstant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

class HS256TokenVerifierTest extends TokenVerifierTestBase {

	private static final String TARGET_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6InBsYWluYmxvY2siLCJhZG0iOmZhbHNlLCJleHAiOjE2NDg2MTUwOTQsImlhdCI6MTY0ODYxMTQ5NH0.Cw5CMpoL5kAacuX6ERlJsLQbpVDj-1XTGmfPMFSqkBE";
	
	@BeforeAll
	static void before() {
		verifier = new HS256TokenVerifier(TestConstant.SECRET);
		mapper = new ObjectMapper();
	}

	@Test
	void decodeTest() {
		DecodedJWT jwt = verifier.decode(TARGET_TOKEN);
		String json = new TokenInfo(jwt).toJson(mapper);
		System.out.println("HS256 decode: " + json);
	}

	@Test
	void verifyTest() {
		VerificationInfo info = verifier.verify(TARGET_TOKEN);
		System.out.println("HS256 verify: " + (info.jwt() != null ? "OK" : "NG"));
	}

}
