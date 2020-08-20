package com.revtaroom.security.jwt;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.revtaroom.dtos.Principal;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

@Component
public class JwtGenerator {
	
	public static String createJwt(Principal subject) {
		
		long nowMillis = System.currentTimeMillis();
		
		JwtBuilder builder = Jwts.builder()
				.setId(Long.toString(subject.getId()))
				.setSubject(subject.getUsername())
				.setIssuer("iagodev")
				.setIssuedAt(new Date(nowMillis))
				.setExpiration(new Date(nowMillis + JwtConfig.EXPIRATION))
				.signWith(JwtConfig.signatureAlg, JwtConfig.SIGNING_KEY);
		
		return JwtConfig.PREFIX + builder.compact();
	}
	
}
