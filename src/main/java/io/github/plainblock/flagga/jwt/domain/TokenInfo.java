package io.github.plainblock.flagga.jwt.domain;

import java.util.Objects;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.plainblock.flagga.jwt.domain.constant.TokenConstant;

public class TokenInfo {

	@JsonProperty("alg")
	private final String algorithm;

	@JsonProperty("iss")
	private final String issuer;

	@JsonProperty("sub")
	private final String subject;

	@JsonProperty("msg")
	private final String message;

	@JsonProperty("adm")
	private final boolean admin;

	@JsonProperty("iat")
	private final long issueAt;

	@JsonProperty("exp")
	private final long expireAt;
	
	public TokenInfo(String algorithm, String issuer, String subject, String message, boolean admin, long issueAt, long expireAt) {
		this.algorithm = algorithm;
		this.issuer = issuer;
		this.subject = subject;
		this.message = message;
		this.admin = admin;
		this.issueAt = issueAt;
		this.expireAt = expireAt;
	}

	public TokenInfo(DecodedJWT jwt) {
		this.algorithm = jwt.getAlgorithm();
		this.issuer = jwt.getIssuer();
		this.subject = jwt.getSubject();
		this.message = jwt.getClaim(TokenConstant.MESSAGE_CLAIM).asString();
		this.admin = jwt.getClaim(TokenConstant.ADMIN_CLAIM).asBoolean();
		this.issueAt = jwt.getIssuedAt().getTime();
		this.expireAt = jwt.getExpiresAt().getTime();
	}

	public String toJson(ObjectMapper mapper) {
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(admin, algorithm, expireAt, issueAt, issuer, message, subject);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TokenInfo other = (TokenInfo) obj;
		return admin == other.admin
				&& Objects.equals(algorithm, other.algorithm)
				&& expireAt == other.expireAt
				&& issueAt == other.issueAt
				&& Objects.equals(issuer, other.issuer)
				&& Objects.equals(message, other.message)
				&& Objects.equals(subject, other.subject);
	}

	@Override
	public String toString() {
		return "TokenInfo [algorithm=" + algorithm
				+ ", issuer=" + issuer
				+ ", subject=" + subject
				+ ", message=" + message
				+ ", admin=" + admin
				+ ", issueAt=" + issueAt
				+ ", expireAt=" + expireAt
				+ "]";
	}

}
