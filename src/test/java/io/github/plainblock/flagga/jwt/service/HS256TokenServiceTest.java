package io.github.plainblock.flagga.jwt.service;

import java.lang.reflect.Field;

import io.github.plainblock.flagga.jwt.domain.constant.TestConstant;
import io.github.plainblock.flagga.jwt.domain.constant.TokenConstant;
import io.github.plainblock.flagga.jwt.domain.generator.HS256TokenGenerator;
import io.github.plainblock.flagga.jwt.domain.generator.TokenGenerator;
import io.github.plainblock.flagga.jwt.domain.verifier.HS256TokenVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

public class HS256TokenServiceTest extends TokenServiceTestBase {

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
        final String decoded = service.decodeToken(EXPIRED_HS256_TOKEN);
        Assertions.assertTrue(decoded.contains(HS256_TOKEN_STRING));
    }

    @Test
    void verifyTokenTest() {
    	TokenGenerator generator = new HS256TokenGenerator(TestConstant.SECRET);
    	String userToken = generator.generate(EXPECTED_SUBJECT, EXPECTED_MESSAGE, false);
    	String adminToken = generator.generate(EXPECTED_SUBJECT, EXPECTED_MESSAGE, true);
    	Assertions.assertFalse(service.verifyToken(userToken).contains("administrator"));
    	Assertions.assertTrue(service.verifyToken(adminToken).contains("administrator"));
    }

    private static HS256TokenService getInstance() {
    	HS256TokenService service = new HS256TokenService();
		try {
			Field generator = HS256TokenService.class.getDeclaredField("generator");
			generator.setAccessible(true);
			generator.set(service, new HS256TokenGenerator(TestConstant.SECRET));
			Field verifier = HS256TokenService.class.getDeclaredField("verifier");
			verifier.setAccessible(true);
			verifier.set(service, new HS256TokenVerifier(TestConstant.SECRET));
		} catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException e) {
			e.printStackTrace();
		}
		return service;
    }
    
}
