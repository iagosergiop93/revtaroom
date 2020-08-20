package com.revtaroom.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revtaroom.dtos.Principal;
import com.revtaroom.security.jwt.JwtDecoder;
import com.revtaroom.security.jwt.JwtGenerator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class PrincipalEncoder {
	
	public static String encodePrincipal(Principal principal) {
		return JwtGenerator.createJwt(principal);
	}
	
	public static Principal decodePrincipal(String token) {
		
		Principal principal = new Principal();
		
		try {
			Claims claims = JwtDecoder.decodeJWT(token);
			
			long id = Long.parseLong(claims.getId());
			String username = claims.getSubject();
			
			principal.setId(id);
			principal.setUsername(username);
			
		} catch(UnsupportedJwtException e) {
			System.out.println("Jwt is invalid");
			throw e;
		}
		
		return principal;
	}
	
}
