package com.revtaroom.security.jwt;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtDecoder {
	
	public static Claims decodeJWT(String jwt) throws UnsupportedJwtException {
	    
		jwt = jwt.replace(JwtConfig.PREFIX, "");
		
		Claims claims = Jwts.parser()
	            .setSigningKey(JwtConfig.SIGNING_KEY)
	            .parseClaimsJws(jwt).getBody();
	    
	    return claims;
	}
	
}
