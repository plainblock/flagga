package io.github.plainblock.flagga.jwt.service;

public interface TokenService {
	
	String generateToken();
	
	String decodeToken(String token);

	String verifyToken(String token);

}
