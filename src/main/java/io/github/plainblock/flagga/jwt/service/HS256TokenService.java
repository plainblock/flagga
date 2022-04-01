package io.github.plainblock.flagga.jwt.service;

import io.github.plainblock.flagga.jwt.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.plainblock.flagga.jwt.domain.TokenInfo;
import io.github.plainblock.flagga.jwt.domain.VerificationInfo;
import io.github.plainblock.flagga.jwt.domain.generator.HS256TokenGenerator;
import io.github.plainblock.flagga.jwt.domain.verifier.HS256TokenVerifier;

@Service
public class HS256TokenService extends TokenServiceBase implements TokenService {

	@Autowired
	private HS256TokenGenerator generator;
	
	@Autowired
	private HS256TokenVerifier verifier;

	@Value(Config.FLAG_HS256)
	private String flag;
	
	public String generateToken() {
		return generator.generate(DEFAULT_SUBJECT, DEFAULT_MESSAGE);
	}
	
	public String decodeToken(String token) {
		TokenInfo info = new TokenInfo(verifier.decode(token));
		return info.toJson(MAPPER);
	}

	public String verifyToken(String token) {
		VerificationInfo info = verifier.verify(token);
		return info.result(flag);
	}
	
}
