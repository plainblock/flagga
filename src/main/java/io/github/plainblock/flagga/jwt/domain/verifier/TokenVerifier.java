package io.github.plainblock.flagga.jwt.domain.verifier;

import com.auth0.jwt.interfaces.DecodedJWT;

import io.github.plainblock.flagga.jwt.domain.VerificationInfo;

public interface TokenVerifier {

    DecodedJWT decode(String token);

    VerificationInfo verify(String token);

}
