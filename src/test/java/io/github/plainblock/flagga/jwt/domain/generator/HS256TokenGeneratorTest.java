package io.github.plainblock.flagga.jwt.domain.generator;

import io.github.plainblock.flagga.jwt.domain.constant.TestConstant;
import io.github.plainblock.flagga.jwt.domain.constant.TokenConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

class HS256TokenGeneratorTest extends TokenGeneratorTestBase {

	@BeforeAll()
	static void before() {
		generator = new HS256TokenGenerator(TestConstant.SECRET);
	}
	
	@Test
	void generateWithSubjectTest() {
		final String token = generator.generate(SUBJECT);
		System.out.println("GenerateWithSubject: " + token);
		DecodedJWT jwt = JWT.decode(token);
		Assertions.assertEquals(SUBJECT, jwt.getSubject());
		Assertions.assertNull(jwt.getClaim(TokenConstant.MESSAGE_CLAIM).asString());
		Assertions.assertFalse(jwt.getClaim(TokenConstant.ADMIN_CLAIM).asBoolean());
	}

	@Test
	void generateWithMessageTest() {
		final String token = generator.generate(SUBJECT, MESSAGE);
		System.out.println("GenerateWithMessage: " + token);
		DecodedJWT jwt = JWT.decode(token);
		Assertions.assertEquals(SUBJECT, jwt.getSubject());
		Assertions.assertEquals(MESSAGE, jwt.getClaim(TokenConstant.MESSAGE_CLAIM).asString());
		Assertions.assertFalse(jwt.getClaim(TokenConstant.ADMIN_CLAIM).asBoolean());
	}

	@Test
	void generateWithAdminFlagTest() {
		final String token = generator.generate(SUBJECT, MESSAGE, true);
		System.out.println("GenerateWithAdminFlag: " + token);
		DecodedJWT jwt = JWT.decode(token);
		Assertions.assertEquals(SUBJECT, jwt.getSubject());
		Assertions.assertEquals(MESSAGE, jwt.getClaim(TokenConstant.MESSAGE_CLAIM).asString());
		Assertions.assertTrue(jwt.getClaim(TokenConstant.ADMIN_CLAIM).asBoolean());
	}

}
