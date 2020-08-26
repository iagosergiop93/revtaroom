package com.revtaroom.security.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revtaroom.dtos.Principal;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

@Component
public class JwtGenerator {
	
	@Autowired
	private JwtConfig jwtConfig;
	
	public String createJwt(Principal subject) {
		
		long nowMillis = System.currentTimeMillis();
		
		JwtBuilder builder = Jwts.builder()
				.setId(Long.toString(subject.getId()))
				.setSubject(subject.getUsername())
				.setIssuer("iagodev")
				.claim("role", subject.getRole().getName())
				.setIssuedAt(new Date(nowMillis))
				.setExpiration(new Date(nowMillis + jwtConfig.EXPIRATION))
				.signWith(jwtConfig.signatureAlg, jwtConfig.SIGNING_KEY);
		
		return jwtConfig.PREFIX + builder.compact();
	}
	
}
