package io.github.plainblock.flagga.jwt.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TokenInfoTest {

	private static final TokenInfo TARGET = new TokenInfo("HS256", "plainblock", "user", "test", false, 600000L, 600000L);
	
	@Test
	void toJsonTest() {
		ObjectMapper mapper = new ObjectMapper();
		final String expected = "{\"alg\":\"HS256\",\"iss\":\"plainblock\",\"sub\":\"user\",\"msg\":\"test\",\"adm\":false,\"iat\":600000,\"exp\":600000}";
		Assertions.assertEquals(expected, TARGET.toJson(mapper));
	}
	
	@Test
	void hashCodeTest() {
		Assertions.assertNotEquals(0, TARGET.hashCode());
	}
	
	@Test
	void equalsTest() {
		final TokenInfo target1 = new TokenInfo("HS256", "plainblock", "user", "test", false, 600000L, 600000L);
		final TokenInfo target2 = new TokenInfo("none", "plainblock", "admin", "test", true, 600000L, 600000L);
		Assertions.assertEquals(target1, TARGET);
		Assertions.assertNotEquals(target2, TARGET);
	}
	
	@Test
	void toStringTest() {
		final String expected = "TokenInfo [algorithm=HS256, issuer=plainblock, subject=user, message=test, admin=false, issueAt=600000, expireAt=600000]";
		Assertions.assertEquals(expected, TARGET.toString());
	}
	
}
