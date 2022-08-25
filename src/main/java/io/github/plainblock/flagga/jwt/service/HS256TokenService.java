package io.github.plainblock.flagga.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.plainblock.flagga.jwt.Config;
import io.github.plainblock.flagga.jwt.domain.TokenInfo;
import io.github.plainblock.flagga.jwt.domain.VerificationInfo;
import io.github.plainblock.flagga.jwt.domain.generator.HS256TokenGenerator;
import io.github.plainblock.flagga.jwt.domain.verifier.HS256TokenVerifier;

@Service
public class HS256TokenService extends TokenServiceBase implements TokenService {

    private final HS256TokenGenerator generator;
    private final HS256TokenVerifier verifier;
    private final String flag;

    @Autowired
    public HS256TokenService(HS256TokenGenerator generator, HS256TokenVerifier verifier, @Value(Config.FLAG_HS256) String flag) {
        this.generator = generator;
        this.verifier = verifier;
        this.flag = flag;
    }

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
