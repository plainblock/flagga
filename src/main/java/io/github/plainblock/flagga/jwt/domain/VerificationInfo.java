package io.github.plainblock.flagga.jwt.domain;

import com.auth0.jwt.interfaces.DecodedJWT;

import io.github.plainblock.flagga.jwt.domain.constant.TokenConstant;

public record VerificationInfo(DecodedJWT jwt, String error) {

    public String result(String flag) {
        if (this.error != null) {
            return tokenInvalid();
        }
        if (this.jwt.getClaim(TokenConstant.ADMIN_CLAIM).asBoolean()) {
            return adminToken(flag);
        }
        return GENERAL_TOKEN;
    }

    private String tokenInvalid() {
        return "This token is invalid: " + this.error;
    }

    private String adminToken(String flag) {
        return "You are administrator. Flag is \"" + flag + "\".";
    }

    private static final String GENERAL_TOKEN = "You are general user. There is no flag.";

}
