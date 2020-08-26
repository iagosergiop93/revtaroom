package com.revtaroom.security.jwt;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.SignatureAlgorithm;

public class JwtConfig {
	
	public final String HEADER = "authorization";
    
	public final String PREFIX = "Bearer ";
    
    public final int EXPIRATION = 24 * 60 * 60 * 1000;
    public final SignatureAlgorithm signatureAlg = SignatureAlgorithm.HS256;
	
	public Key SIGNING_KEY;
	
    public JwtConfig() {
        super();
    }
	
}
