package io.github.plainblock.flagga.jwt.domain.generator;

public interface TokenGenerator {
	
	String generate(String subject);
	
	String generate(String subject, String message);
		
	String generate(String subject, String message, boolean isAdmin);

}
