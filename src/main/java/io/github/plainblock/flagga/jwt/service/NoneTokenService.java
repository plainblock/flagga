package io.github.plainblock.flagga.jwt.service;

import io.github.plainblock.flagga.jwt.Config;
import io.github.plainblock.flagga.jwt.domain.VerificationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.plainblock.flagga.jwt.domain.TokenInfo;
import io.github.plainblock.flagga.jwt.domain.generator.NoneTokenGenerator;
import io.github.plainblock.flagga.jwt.domain.verifier.NoneTokenVerifier;

@Service
public class NoneTokenService extends TokenServiceBase implements TokenService {

	@Autowired
	private NoneTokenGenerator generator;

	@Autowired
	private NoneTokenVerifier verifier;

	@Value(Config.FLAG_NONE)
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
