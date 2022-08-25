package io.github.plainblock.flagga.jwt.domain.verifier;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.github.plainblock.flagga.jwt.domain.constant.TokenConstant;
import io.github.plainblock.flagga.jwt.domain.VerificationInfo;

@Component
public class NoneTokenVerifier implements TokenVerifier {

    private final JWTVerifier verifier;

    public NoneTokenVerifier() {
        this.verifier = JWT.require(Algorithm.none())
                .withIssuer(TokenConstant.ISSUER)
                .build();
    }

    @Override
    public DecodedJWT decode(String token) {
        try {
            return JWT.decode(token);
        } catch (JWTDecodeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public VerificationInfo verify(String token) {
        try {
            final DecodedJWT jwt = verifier.verify(token);
            return new VerificationInfo(jwt, null);
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return new VerificationInfo(null, e.getMessage());
        }
    }

}
