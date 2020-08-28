package com.revtaroom.security.jwt;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtDecoder {
	
	private JwtConfig jwtConfig;
	
	public JwtDecoder(JwtConfig jwtConfig) {
		super();
		this.jwtConfig = jwtConfig;
	}



	public Claims decodeJWT(String jwt) throws UnsupportedJwtException {
	    
		jwt = jwt.replace(jwtConfig.PREFIX, "");
		
		Claims claims = Jwts.parser()
	            .setSigningKey(jwtConfig.SIGNING_KEY)
	            .parseClaimsJws(jwt).getBody();
	    
	    return claims;
	}
	
}
