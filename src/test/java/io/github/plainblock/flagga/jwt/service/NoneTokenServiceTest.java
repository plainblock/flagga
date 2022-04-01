package io.github.plainblock.flagga.jwt.service;

import java.lang.reflect.Field;

import io.github.plainblock.flagga.jwt.domain.constant.TokenConstant;
import io.github.plainblock.flagga.jwt.domain.generator.NoneTokenGenerator;
import io.github.plainblock.flagga.jwt.domain.generator.TokenGenerator;
import io.github.plainblock.flagga.jwt.domain.verifier.NoneTokenVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

public class NoneTokenServiceTest extends TokenServiceTestBase {

	@BeforeAll
	static void before() {
		service = getInstance();
	}

    @Test
    void generateTokenTest() {
        final String token = service.generateToken();
        DecodedJWT jwt = JWT.decode(token);
        Assertions.assertEquals(EXPECTED_SUBJECT, jwt.getSubject());
        Assertions.assertEquals(EXPECTED_MESSAGE, jwt.getClaim(TokenConstant.MESSAGE_CLAIM).asString());
        Assertions.assertFalse(jwt.getClaim(TokenConstant.ADMIN_CLAIM).asBoolean());
    }

    @Test
    void decodeTokenTest() {
        final String decoded = service.decodeToken(EXPIRED_NONE_TOKEN);
        Assertions.assertTrue(decoded.contains(NONE_TOKEN_STRING));
    }

    @Test
    void isAdminTokenTest() {
    	TokenGenerator generator = new NoneTokenGenerator();
    	String userToken = generator.generate(EXPECTED_SUBJECT, EXPECTED_MESSAGE, false);
    	String adminToken = generator.generate(EXPECTED_SUBJECT, EXPECTED_MESSAGE, true);
		Assertions.assertFalse(service.verifyToken(userToken).contains("administrator"));
		Assertions.assertTrue(service.verifyToken(adminToken).contains("administrator"));
    }
    
    private static NoneTokenService getInstance() {
    	NoneTokenService service = new NoneTokenService();
		try {
			Field generator = NoneTokenService.class.getDeclaredField("generator");
			generator.setAccessible(true);
			generator.set(service, new NoneTokenGenerator());
			Field verifier = NoneTokenService.class.getDeclaredField("verifier");
			verifier.setAccessible(true);
			verifier.set(service, new NoneTokenVerifier());
		} catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException e) {
			e.printStackTrace();
		}
		return service;
    }

}
