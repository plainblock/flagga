package io.github.plainblock.flagga.jwt.domain.generator;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.github.plainblock.flagga.jwt.Config;
import io.github.plainblock.flagga.jwt.domain.constant.TokenConstant;

@Component
public class HS256TokenGenerator implements TokenGenerator {

	private final Algorithm algorithm;

	public HS256TokenGenerator(@Value(Config.JWT_SECRET) String secret) {
		this.algorithm = Algorithm.HMAC256(secret);
	}

	public String generate(String subject) {
		return generate(subject, null, false);
	}

	public String generate(String subject, String message) {
		return generate(subject, message, false);
	}

	public String generate(String subject, String message, boolean isAdmin) {
		final long now = System.currentTimeMillis();
		try {
			return JWT.create()
					.withIssuer(TokenConstant.ISSUER)
					.withSubject(subject)
					.withIssuedAt(new Date(now))
					.withExpiresAt(new Date(now + TokenConstant.EXPIRE))
					.withClaim(TokenConstant.ADMIN_CLAIM, isAdmin)
					.withClaim(TokenConstant.MESSAGE_CLAIM, message)
					.sign(algorithm);
		} catch (JWTCreationException e) {
			e.printStackTrace();
		}
		return null;
	}

}
