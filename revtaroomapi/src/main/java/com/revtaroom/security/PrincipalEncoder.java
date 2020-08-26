package com.revtaroom.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revtaroom.dtos.Principal;
import com.revtaroom.entities.UserRole;
import com.revtaroom.security.jwt.JwtDecoder;
import com.revtaroom.security.jwt.JwtGenerator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class PrincipalEncoder {
	
	@Autowired
	private JwtGenerator jwtGenerator;
	
	@Autowired
	private JwtDecoder jwtDecoder;
	
	public String encodePrincipal(Principal principal) {
		return jwtGenerator.createJwt(principal);
	}
	
	public Principal decodePrincipal(String token) {
		
		Principal principal = new Principal();
		
		try {
			Claims claims = jwtDecoder.decodeJWT(token);
			
			long id = Long.parseLong(claims.getId());
			String username = claims.getSubject();
			UserRole role = new UserRole();
			role.setName(claims.get("role", String.class));
			
			principal.setId(id);
			principal.setUsername(username);
			principal.setRole(role);
			
		} catch(UnsupportedJwtException e) {
			System.out.println("Jwt is invalid");
			throw e;
		}
		
		return principal;
	}
	
}
