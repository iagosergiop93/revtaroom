package com.revtaroom.security.jwt;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtConfig {
	
	public static final String HEADER = "authorization";
    
	public static final String SECRET = "secret";
	
	public static final String PREFIX = "Bearer ";
    
    public static final int EXPIRATION = 24 * 60 * 60 * 1000;
    public static final SignatureAlgorithm signatureAlg = SignatureAlgorithm.HS256;
    public static final Key SIGNING_KEY;
    
    static {
    	
    	System.out.println("header - " + HEADER);
    	
    	System.out.println("secret - " + SECRET);
    	
        byte[] secretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        SIGNING_KEY = new SecretKeySpec(secretBytes, signatureAlg.getJcaName());
    }
    
    private JwtConfig() {
        super();
    }
	
}
